# Smart Pointers
```c++
#include <memory>
```
**smart pointer**是一个模板类，通过封装方法，使其使用起来和指针类似，但是其具有构造函数和析构函数。同时smart pointer通过引用计数器来智能管理对象的内存。
- std::shared_ptr 允许多个指针指向同一个对象
- std::unique_ptr 独占所指向的对象
- std::weak_ptr 很少使用，主要结合shared_ptr使用

##  Shared_ptr
shared_ptr将new、delete等操作符封装成一个模板类，可以拷贝和复制。
```c++
shared_ptr<T> p1=p;// 引用同一个对象，对同一对象有所有权。此操作会导致p的引用计数+1，而p1原先引用对象的引用计数-1。该操作之后，p1的引用计数等于p的引用计数。
shared_ptr<T> p1(p);//p1是p的拷贝。此操作会递增p的计数器。p的类型要求可以转换为T*。
```

当一个对象引用的最后一个指针（the last shared_ptr pointing to an object）被销毁后，shared_ptr class自动回收内存空间。这一机制是通过shared_ptr class的析构函数实现的。shared_ptr的析构函数会递减它所指向对象的引用次数。如果引用次数为0，shared_ptr的析构函数会销毁对象，并释放它所占用的内存。

**Example:**
```c++
#include <iostream>
#include <memory>
class Test(){
    public: Test(){std::cout<<"Test()"<<std::endl;}
    ~Test(){std::cout<<"~Test()"<<std::endl;}
};
int main(){
    // 生成一个空对象（Test类类型）
    std::shared_ptr<Test> p1=std::make_shared<Test>();
    std::cout<<p1.use_count()<<std::endl;
    {
        // 限制作用域
        std::shared_ptr<Test> p2=p1;
        std::cout<<p1.use_count()<<std::endl;
    }
    std::cout<<p1.use_count()<<std::endl;
    return 0;
}
```
Output:
```c++
Test();// make_shared<Test>()会new一个Test的对象，因此Test中的构造函数被调用
1// p1的计数为1
2
1
~Test()
// main函数结束后，main函数中的shared_ptr p1被销毁，并且计数-1，此时p1的计数为0。从而p1的析构函数会销毁之前new出来的对象。该对象由Test的析构函数销毁。
```
## shared_ptr的生命周期

```c++
shared_ptr<F> foo(T arg){
    // process arg as appropriate
    // shared_ptr will take care of deleting this memory
    return make_shared<F>(arg);
}
```
以上的foo函数通过make_shared（通过new）构造了一个指向F类型的shared_ptr。而且foo将其作为结果返回。因此foo分配的对象

```c++
void foo(T arg){
    shared_ptr<int> p=make_shared<int> (42);
    // do something;
    // return ;
}
```
这时候，p指向的是一个通过make_shared new出来的对象（占据一定new出来的内存空间），当foo()调用结束时，p的生命周期结束，调用p的析构函数，此析构函数中包含delete。从而实现内存管理的智能性。此外，p是局部变量，离开作用域将会被销毁，但是其引用的对象若有其他shared_ptr指针引用着，也就是说，该对象的引用计数不为0.则该内存将不会被释放。

**环形引用**
```c++
class TB;
class TA{
    public:TA(){...};
    void refB(std::shared_ptr<TB> ptr)this->ptr=ptr;

    ~TA(){...}
    private:
    std::shared_ptr<TB> ptr;
};
class TB{
    public:TB(){...};
    void refA(std::shared_ptr<TA> ptr)this->ptr=ptr;
    ~TB(){...};
    private:
    std::shared_ptr<TA> ptr;
};
int main(){
    std::shared_ptr<TA> pa=std::make_shared<TA>();
    std::shared_ptr<TB> pb=std::make_shared<TB>();//new一个TB类型的对象，调用TB();

    pa->refB(pb);
    pb->refA(pa);
    return 0;
}
```
输出结果：
```c++
TA()
TB()
```
即以上example说明只有TA()和TB()被调用。而pb和pa在程序结束后，销毁，同时pb和pa引用对象的引用计数减一。但是该生成（new）对象的引用计数此时还是为1，因此并没有销毁该生成对象，也就是没有调用该对象的析构函数。因此造成了内存泄漏。
