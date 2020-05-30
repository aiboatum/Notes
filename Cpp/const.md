# const限定符

1. `const`对象一般是不可更改的，可以通过一些特殊手段更改，如通过`const_cast`去掉底层`const`，但结果可能是未定义的。
2. `const`对象可以编译时初始化，也可以运行时初始化。
2. `const`对象创建时就要初始化，原因和引用一样（创建后就不能再改变，这里又设计到赋值和初始化的差别，hhh）
    ```c++
    const int val;  // 未被初始化，错误 
    val=2;  // 这里是赋值，即用新的值替换旧的值（不论旧的是否是被初始化的值）
    ```
3. **默认情况下**，`const`对象仅在文件内有效。 
    - 这是因为编译初始化时，等价于C中的宏替换，此时编译器必须知道`const`对象的值。从而各个文件应当能够访问到该初始值才行。因此，每个文件中应当有对它的定义，因此默认情况下，。。。仅在文件内有效。多个文件内的同名`const`对象，是分别独立的变量。
    - 当`const`对象时运行时初始化时，为了让多个文件共享一个`const`对象的初始值，使用定义和声明分离的方法，即方法和非常量对象一样的方法，使用`extern`。
    ```c++
    // file1.cc 定义并初始化一个常量
    extern const int bufsize=func();
    // file1.h
    extern const int bufsize;
    ```
## 对const的引用

```c++
double dval=3.14;
const int &ri=dval;
```
以上代码编译器会这样对待
```c++
const int temp=dval;    // 有一个浮点数生成一个临时的int常量
const int &ri=temp;
```
这里可以看出，`temp`是一个右值，因此只有一个指向常量的左值引用才可以绑定一个右值。

## const指针

区分`const`指针和指向`const`的指针：
```c++
int val=2;
// const int val=2;
const int *p=&val;              // 指向const的指针
const int * const pconst=&val;  // const指针
```

> 引用绑定的对象都是不可更改的，因此没有`const int &const r`这种用法。

## 顶层const和底层const

- **顶层const**是指针本身的属性，如一个指针是`const`指针还是`nonconst`。
- **底层const**是指针指向的对象的属性。

> 更精确点，应该是如果`const`修饰的是变量本身就是顶层。

```c++
int i=0;
int *const p1= &i;  // 指针p1不可以改变，顶层
const int ci=42;    // ci不可改变，因此是顶层
const int *p2=&ci;  // p2可以改变，底层
const int *const p3=p2; // 左边是底层，右边是顶层
```
值得注意的是`const int &r=ci`，声明用的`const`都是底层，这是因为可以看做，引用那边有个省略的`const`，类似于
```c++
const int & const r=ci; // 仅用作示意，左边是底层const，右边是顶层const，但是右边实际上是不存在的
// 类比于
const int *const p3=p2;
```