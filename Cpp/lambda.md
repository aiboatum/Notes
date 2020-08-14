# lambda 表达式

lambda 表达式，就是一个匿名函数。实际上，定义一个 lambda 时，**编译器生成一个与 lambda 对应的未命名的类类型**。

形式如下：
```c++
[capture list](parameter list)-> return type{function body}
```
其中，
- capture list 是一个 `lambda` 所在函数中定义的局部变量的列表，通常为空
- lambda 必须尾置返回，指定返回类型（这个返回类型可以由编译器推断出来）
- capture list 有两种捕获方式：
    1. 值捕获 cpature by value
    2. 引用捕获 capture by reference

## 值捕获

值捕获的前提是变量可以拷贝。如
```c++
void fnc1(){
    size_t v1=42;
    auto f=[v1]{return v1;};
    v1=0;
    auto j=f(); // j 为 42，f 中的捕获列表是在创建时进行的拷贝，而不是调用时
}
```

## 引用捕获

以引用的方式捕获局部变量，但是应注意引用的局部变量的生命周期。

## 隐式捕获

除了显示列出想要捕获的变量外，还可以让编译器根据 lambda 体中的代码推断我们要使用哪些变量，为了指示编译器推断捕获列表，应在捕获列表内写一个 `=` 或 `&`：
- `=` 表示采用值捕获
- `&` 表示采用引用捕获
- 也可以混合，一部分采用值捕获，一部分采用引用捕获，用法如下
    ```c++
    // 第一个 & 或 = 指定了，默认的捕获方式，后面的显示指出个别变量是引用捕获还是值捕获
    [&,c](parameter list){function body};   
    [=,&os](parameter list){function body};
    ```

> 对于一个值拷贝的变量，默认情况下，lambda 不会改变其值，如果需要修改被捕获的变量的值，必须使用 `mutable` 关键字。引用捕获方式则不需要。

```c++
auto f=[v1]()mutable {return ++v1;}
```

# 参数绑定

当我们定义的函数，是一个二元谓词的函数，简单理解为接受两个形参的函数。**谓词**的概念查看 [Predicate.md](Predicate.md)。如
```c++
bool check_size(const string &s,string::size_type sz){return s.size()>=sz;}
```
而 `find_if` 接受一个一元谓词，因此传递给 `find_if` 的可调用对象必须接受单一参数。当然可以使用 lambda 来代替这个函数，即使用捕获列表保存 `sz`，或者使用 `std::bind` 函数。

## bind函数

`bind` 函数定义在 `functional` 头文件中，`bind` 函数可以看作一个通用的**函数适配器**，接受一个可调用对象，生成一个新的可调用对象，来适应原对象的参数列表。调用方式为
```c++
auto newCallable=bind(callable,arg_list);
```
很显然，当我们调用 `newCallable` 时，`newCallable` 会调用 `callable`，并传递 `arg_list` 中的参数。

`arg_list` 中参数是形如 `_n` 的名字，这些参数是『占位符』。表示 `newCallable` 的参数，它们占据了传递给 `newCallable` 的参数的『位置』。

例如，现在绑定 `check_size` 函数，如下，
```c++
auto check6=bind(check_size,_1,6);
string s="hello";
bool  b1=check6(s); // check6 调用 check_size（s,6)
```
此 `bind` 绑定只有一个占位符，表示 `check6` 只接受单一参数。

### bind 的参数

更一般的解释占位符，即
```c++
// _n定义在命名空间placeeholders中
using std::placeholders::_1;
using std::placeholders::_2; 
auto g=bind(f,a,b,_2,c,_1);
```
`g` 接受的参数是2个，分别用占位符 `_2,_1` 表示，这个新的可调用对象 `g` 将它自己的参数作为第三个和第五个参数传递给 `f`，`f` 的第一个、第二个、第四个参数分别绑定到给定的 `a,b,c` 上。

此外，`_2,_1` 的位置会影响传递参数的过程，传递给 `g` 的参数将，即第一个参数绑定到 `_1`，第二个 `_2`。当我们调用 `g` 的时候，其第一个参数作为 `f` 的最后一个参数，依次类推。如
```c++
g(_1,_2) 将会被bind调用映射为f(a,b,_2,c_,_1)
g(X,Y)，将会调用f(a,b,Y,c,X)。
```

