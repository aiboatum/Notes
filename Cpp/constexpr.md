# constexpr和常量表达式

**常量表达式**是指值不会改变并且在**编译过程**就能得到计算结果的表达式。

> 用来编译时替换。因此非常量的变量不适用。

1. 字面值属于常量表达式
2. 编译时初始化的`const`属于常量表达式，运行时初始化的`const`显然不属于


## constexpr

**constexpr**，C++11引入，便于编译器验证一个表达式是否为常量表达式。目的就是可以确保编译时可以进行替换。