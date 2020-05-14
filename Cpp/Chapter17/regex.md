# 正则表达式

## 用法

C++的regex使用的时ECMAScript。不同之处：
- [[:alpha:]]匹配任意字母，其他regex语言中的`.`

```c++
#include <regex>
str="[[:alpha:]]";
regex pattern(string(str));
smatch results; // 保存搜索结果
string test_str="......";
if(regex_search(test_str,results,pattern))cout<<results.str();
```
`regex_search`，如果找到匹配子串，返回true。并且，`regex_search`只要找到一个匹配子串就会停止查找。

一般只需要记住三个即可，其他用到再查。
```c++
regex_replace
regex_search
regex_match
```