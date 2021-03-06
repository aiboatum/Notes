# 类

> 以下为类中需要注意的点。

1. 类内定义的成员函数默认为 `inline`，类外定义的成员函数，如希望 `inline`，应加上 `inline` 关键词。
2. 类中可定义**可变数据成员**，使用 `mutable` 关键词，该成员即使是 `const` 对象的成员，也可以改变其值（从这里也可以看出 `const` 关键词不是真正的绝不可修改）
3. 类的对象创建之前，一定要被定义。
4. 类定义中不可以包含自身，但可以是指向自身的指针或引用。
    ```c++
    struct listnode{
        int val;
        listnode *next;
    };
    ```


## this 指针

```c++
// 当我们声明一个对象
class Foo{/*...*/};
Foo obj;
obj.fun();
```
这里的 `obj.fun()` 实际上，该调用类似于 C 中的函数调用，只不过类的成员函数参数中包含一个 `this` 指针。类似于
```c++
fun（&obj); // 这种就和C中类似了
```
调用类的成员函数 `fun()` 时，传入了 `obj` 的地址给 `this` 指针。

> `this` 是一个指针常量，不允许改变 `this` 中保存的地址。

refercence: 
1. [C++为什么要弄出虚表这个东西？ - 果冻虾仁的回答 - 知乎](https://www.zhihu.com/question/389546003/answer/1194780618) 该回答中可以看出，成员函数到底在底层是如何分布的。

## const 成员函数

`const` 成员函数，即用来修饰 `this` 指针为 `const Foo *const this` 的，其旨在说明该成员函数不会改变 `this` 指针指向对象的值。
```c++
void Foo::fun_const()const{/* 不修改this指向的对象 */};
// 等价于下面的语句，当然下面的语句是非法的，仅用作说明
void Foo::fun_const(const Foo *const this){};
```
这样，`this` 指针就可以指向 `const` 对象。

## 编译器合成函数

编译器可以合成默认构造函数，拷贝、赋值、析构等操作函数。
```c++
class Foo{
    public:
        Foo()=default;
        Foo(const Foo &)=default;
        Foo& operator=(const Foo&)=default;
        ~Foo()=default;
};
```

## 友元 friend

1. 类中的友元声明可以出现在类中任何位置，不论是 `public` 区域还是 `private` 区域，因为**这只是个友元声明**。但是最好定义在最前面或最后面。
2. 类中的友元声明仅仅是为了告诉类，这有一个友元，类外其他函数调用友元函数，应当还需要一次声明。但是**编译器并没强制这样做**。
3. 友元的定义，如友元函数可以定义在类内友元声明时给出定义，但是一般最好在外部，如同普通函数一样定义。

## 委托构造函数

**委托构造函数**就是委托其他构造函数构造。如
```c++
class Foo{
public:
    // 非委托构造函数
    Foo(string s,unsigned cnt,double price):str(s),totol_cnt(cnt),revenue(cnt*price){}
    // 其余构造函数委托给另一个构造函数
    Foo():Foo("",0,0){}
    Foo(string s):Foo(s,0,0){}
    Foo(istream &is):Foo(){/* do something using istream is */;}
};
```

## 隐式的类类型转换

> 能通过一个实参调用的构造函数定义了一条从构造函数的参数类型向类类型隐式转换的规则。多个实参的构造函数没有这种规则。


例如，在 `Foo` 类中，有一个接受 `string` 的构造函数，和接收 `istream` 的构造函数分别定义从这两种类型向 `Foo` 隐式转换的规则，即在使用 `Foo` 的地方，可以使用 `string` 和 `istream` 对象代替。
```c++
string s="...";
Foo obj;
obj.func(s);    // func的形参是Foo类型
```
上面的 `s` 会被构造一个临时的 `Foo` 对象。

> 这种隐式转换只可以 one step，即不可以多层嵌套这种构造。
```c++
obj.func(string("..."));    // ok，显示构造一个string临时对象，该string对象隐式转换为Foo
obj.func(Foo("..."));       // ok，隐式的转换为一个string对象，该对象被显示构造一个Foo临时对象

obj.func("...");            // error，不可以进行两次隐式转换
```

### 抑制构造函数定义的隐式转换

使用 `explicit`：
```c++
class Foo{
public:
    Foo()=default;
    Foo(string s,unsigned cnt,double price):str(s),totol_cnt(cnt),revenue(cnt*price){}
    explicit Foo(const string &s):str(s){}
    explicit Foo(istream &);
};
```
这里就不可以使用一个 `string` 对象隐式构造一个 `Foo` 对象。即
```c++
obj.func(string("..."));    // error，Foo(const string *s)是explicit的
obj.func(Foo(string("...")));   // ok
```
或者使用 `static_cast` 转换。
```c++
// static_cast可以使用explict的构造函数
obj.func(static_cast<Foo>(cin));
```

## 聚合类和字面值常量类

- 聚合类就是满足一些限制的类，如
    - 成员都是 `public` 的
    - 没有构造函数
    - 没有类内初始值
    - 没有基类，没有 `virtual` 函数
- 字面值常量类也是满足一些限制的类，如
    - 数据成员都是字面值类型
    - 至少包含一个 `constexpr` 构造函数
    - 。。。

