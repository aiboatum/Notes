# new/delete 和 malloc/free

对于 `new/delete` 和 `malloc/free` ：

1. `new/delete` 是操作符， `malloc/free` 是函数【前者可以重载，后者没有这个概念】
2. `new/delete` 执行构造函数和析构函数，后者则没有
3. `new` 申请的空间会被初始化， `malloc` 不会
4. `new` 自动计算分配大小， `malloc` 手动计算

```c++
int * p = (int *)malloc(sizeof(int)); 
int * p1 = new int; 

``` 
5. `malloc` 返回的是 `void *` 类型，需要强制转换
6. `new` 内存申请失败，抛出 `bad_alloc` 异常， `malloc` 返回 `NULL`

## 示意图

其中， `new/delete` 和 `malloc/free` 的关系如下：
```c++
new -->(size)--> operator new() --> malloc() --> constuctor -->(return ptr)

delete -->(ptr)--> destructor --> operator delete() --> free()

```

`new[]/delete[]` 和 `malloc/free` 的关系
```c++
new[count] --> size+4 --> operator new[]() --> operator new() --> malloc --> constructor -->(return ptr)
delete -->(ptr)

delete[] -->(ptr)-->constructor(调用 count 次)-->operator delete[]() --> operator delete() --> free()

``` 

## new/delete 和 new[]/delete[]

对于自定义类型来说，如

``` c++
class Foo; 
Foo * pf=new Foo[10]; 
delete [] pf; 

```

其中， `new Foo[10]` 会调用 `Foo` 的默认构造函数，执行 10 次。然后调用 `operator new[]()` ，该函数调用 `operator new` 。底层使用 `malloc` 申请内存。

类似地， `delete[] pf` 会分三部分完成内存释放的工作：

* 依次调用 `Foo` 的析构函数，执行 10 次
* 调用 `operator delete[]()` ，它调用 `operator delete`
* 底层调用 `free` 释放内存

## new 出来的空间包含额外的 4 字节空间

1. 编译器对 `new[]` 出来的空间头部，使用 4 字节空间存储申请对象的个数
2. `delete[]` 删除时候，无需向 `free` 一样显示传递给它大小，因为这个大小已经保存起来了
    - `delete[]` 将 `new []` 返回的地址向前移 4 字节大小，就可以得到需要析构对象的大小

> `new type[]` ，只有 `type` 显示定义析构函数时，才需要多开 4 个字节保存对象的个数。所以 `new int` 这种内存类型，编译器不会多开 4 个字节，编译器会自动优化。

## new/delete, new[]/delete[] 配套使用

### 自定义类型（需要显示定义析构函数的类型）

对于需要显示定义析构函数的类型，那么 `new/delete` 要配对使用， `new[]/delete[]` 要配对使用。

如果不配对使用，则不会调用析构函数，从而回收构造函数中申请的内存资源。如类
```c++
class Foo {
private:

    int* _a;

public:

    Foo(size_t count = 1)
    {
        _a = new int[count];
        cout << "Foo constructor" << endl;
    }
    ~Foo()
    {
        delete[] _a;
        cout << "Foo destructor" << endl;
    }

}; 

``` 
有如下调用的时候，
```c++
Foo * p = new Foo; 
free(p); // 不报错，但是不会调用析构函数，p 中构造函数申请的空间没有被释放

Foo * p1 = new Foo[10]; 
delete p1; // 崩溃，释放位置被后移了 4 字节，而且只会调用 1 次析构函数

Foo * p2 = new Foo; 
delete[] p2; // 报错，非法访问内存。因为 p2 没有额外的 4 字节，而该语句试图前移 4 字节，而寻找调用析构函数的次数

```

### 内置类型（或者不需要显示定义析构函数的类型）

编译器对于内置类型，不会多开 4 个字节来保存 `new` 对象的个数。以及内置类型，析构函数可调用也可不调用，因为不涉及到内存泄漏的问题。可以看出，对于
```c++
int * p = new int [10]; 
delete p; // ok
int * p2 = new int [10]; 
delete [] p2; // ok
```

`delete[]` 内置类型时，不需要和 `new[]` 配套使用，但是配套使用总是没错的。

> C++ 中，多有的类型（包括内置类型）都可以视为类类型，即包含构造函数和析构函数的。故而 `int a(10)` 也是合法的。
