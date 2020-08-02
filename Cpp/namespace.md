# namespace 

基本用法：
```c++
namespace std{
    ...
}
```

## 用法
- **using directive**，也是最不安全的一种用法，非常容易冲突
```c++
#include <iostream>
using namespace std;
```
此时可以使用namespace std中全部的内容。

- **using declaration**
```c++
#include <iostream>
using std::cout;
using std::cin;
```
此时，只可以使用`cout`和`cin`。
