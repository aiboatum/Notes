# STL

> STL可以看成是C++标准库的子集，约占90%。

## STL六大组件

1. 容器（container）：vector，list，deque，set，map等等，用来存放数据
2. 算法（algorithm）：sort，search，copy，erase等等，这里的算法可以看成“泛型”函数或模板函数（function template）
3. 迭代器（iterator）：泛型的指针，字面意思即通过迭代器遍历某个容器或其他。很显然，数组下标就是一种迭代器。容器设计者知道如何访问元素的操作进行了包装。
4. 仿函数（functors）：行为类似函数，如function-object class，即定义了`()`操作符的类或类模板。（c++11引入的lambda表达式）
5. 适配器（adapters）：容器适配器，仿函数适配器（这个现在很少用到），迭代器适配器。如STL种的queue和stack就是利用容器deque实现的两个适配器，即对deque的一些操作进行包装。
6. 空间分配器（allocators）：负责空间配置与管理。

### 六大部件的关系

空间配置器负责容器的内存管理，即container必须通过allocator进行内存管理；algorithm通过iterator存取container种的内容，functor协助algorithm完成不同的策略，adaptor可以一定的适配工作。
```c++
#include <vector>
#include <algorithm>
#include <functional>
#include <iostream>
using namespace st;
int main(){
    int ia[]={1,2,3,4};
    vector<int,allocator<int> > vi(ia,ia+4);
    cout<<count_if(vi.begin(),vi.end(),
        [](const int &a){
            return a<40;
        });
    cout<<cout_if(vi.begin(),vi.end(),
        std::bind(less<int>(),std::placeholders::_1,40));
    return 0;
}
```

其中，`vector`是container，`allocator`是`allocators`空间配置器，`vi.begin()`是iterators，`count_if()`是algorithm；

`bind`是function adaptor，`less<int>()`是function object；整个`count_if`第三个参数称为**谓词**（predicate）--数理逻辑中的概念。这里的lambda表达式是一阶谓词，即接收一个参数。