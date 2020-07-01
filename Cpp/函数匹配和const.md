# 函数匹配和const的关系

`const`对函数匹配的影响
## 参数传递会忽略顶层`const`
1. 形参是非引用或非指针类型
```c++
// 以下两个版本调用时会引起二义性，不知道调用哪一个
void foo(const int a);  // 这里的const是顶层const，传递参数时会忽略
void foo(int a);
```
而且，从另一个角度，由于形参是非引用，因此采用拷贝方式初始化形参。故
- `const int a`既可以用`int`类型的对象初始化，也可以用`const int`类型的初始化，因此会忽略该顶层`const`
- `int a`也是，既可以用`int`。。。

> 拷贝初始化，形参和实参互相独立，互不影响。

2. 形参是指针类型
```c++
void foo(const int *a); // 这里的const不是顶层const，是底层const，因此不会忽略
void foo(int *a);
```
- 这里的`const int *a`，很显然可以指向（绑定）到一个`const int`也可以绑定到`int`
- 第二个`int *a`，很显然不可以指向一个`const int`对象
因此，这是两个可以在匹配过程可以区分的两个函数。

3. 形参是引用类型
```c++
void foo(const int &a); // 这里的const是底层const，顶层const隐含在引用中，即引用可以看成一个指针常量
                        // & ==== *const ，两者是等价的。
void foo(int &a);
```
和指针一样，第二个不可以绑定到`const int`类型对象。第一个两个都可以。因此，也可以区分两个函数。

### 成员函数可以通过有无`const`来区分

由前面所述，成员函数中`const`修饰的是`this`指针，故可以区分。

