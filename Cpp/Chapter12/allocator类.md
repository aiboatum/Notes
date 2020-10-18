# allocator类

```c++

#include <memory>

``` 
为什么要引入 `allocator` 类，负责内存管理？

1. `new` 将内存分配和对象的构造组合在一起， `delete` 将对象析构和内存释放组合在一起。 `allocator` 类可以将内存分配和对象构造分离开来。只有真正需要时才真正执行对象创建操作。 

一般来说，内存分配和对象构造组合在一起可能会有一定的浪费。
```c++
string *const p = new string[n];  // 分配内存，且构造了n个空string
string s;
string *q = p;                    // q指向第一个string
while(cin >> s && q != p + n)
    *q++ = s;
const size_t size = q-p;          // 记录读取了多少个string
// 使用数组
delete [] p;                    
```

`new` 表达式分配并初始化 n 个 `string` ，但是，我们可能不需要 n 个 `string` ，少量 `string` 可能就足够了。这样，我们就可能创建了一些永远用不到的对象。而且，对于确实要使用的对象，我们也在初始化之后立即赋予了它们新值。每个使用到的元素都被“赋值”了两次：第一在默认初始化时，随后是在赋值时。

> 更重要的是，没有默认构造函数的类就不能动态分配数组。

## allocator类

`allocator` 分配的内存是原始的（raw），未构造的（unconstructed）。

```c++

allocator<string> alloc; // 可以分配string的allocator对象
auto const p=alloc.allocate(n); // 分配n个未初始化的string

``` 

### construct 函数

`construct` 成员函数负责在给定位置构造一个元素。
```c++
auto q = p;   // q指向最后构造的元素之后的位置
alloc.construct(q++);       // 没有指定额外的参数，即没有显示指定如何初始化构造元素，则使用默认构造
                            // *q为空string，然后q后移
alloc.construct(q++,10,'c');    // *q为cccccccccc，然后q后移
alloc.construct(q++,"hi");         // *q为hi，然后q后移
```

> `construct` 可以使用对象的任何构造函数构造元素。

很显然，使用未构造的对象是非法的行为，而且是很危险的。

### destroy 函数

`destroy` 成员函数负责销毁 `construct` 构造的函数，**但是不负责回收之前allocate函数分配的内存**。
```c++

while(q!=p){

    alloc.destroy(--q);     // 释放真正构造的string，当然，未构造的是不用也是不允许destroy的

}

``` 

### deallocate 函数

`deallocate` 成员函数负责回收内存，归还给系统。
```c++
alloc.deallocate(p,n);  // 这里的p不可以为空，这里的n必须是分配时的大小
```

### 拷贝和填充未初始化内存的算法

为了方便在未构造的内存中创建对象，提供以下算法（函数）
```c++
uninitialized_copy(beg, end, beg2); // 从迭代器beg和end范围内，拷贝元素到迭代器

                                    // beg2指定的未构造的原始内存中，其中beg2指向的内存必须足够大
                                    // 返回beg2，即最后一个构造元素的后一位

uninitialized_copy_n(beg, n, beg2); 
uninitialized_fill(beg, end, t); // 类似于memset，将在beg和end范围内的原始内存赋于t
uninitialized_fill_n(beg, n, t); 

``` 
例如，
```c++
auto p = alloc.allocate(vi.size()*2);     // 分配原始内存
auto q = unitialized_copy(vi.begin(),vi.end(),p);
uninitialized_fill_n(q,vi.size(),42);
```

> 类似 `copy` ， `uninitialized_copy` 返回 （递增后的）目的位置迭代器。
