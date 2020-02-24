# Using is one of type alias.
- syntax
```c++
using indentifier attr(optiaonal)=type-id;
```
**explanation of using**
```c++
#include <iostream>
int main(){
    using integer=INT;
    integer a=2;//equivalent to int a=2;

    using pstring=const char *;
    pstring s="123";
    std::cout<<s;
    
}

// moreover
using F =int (int *,int);// F is a  function type , not pointer to function

using PF=int(*) (int *,int );// PF is a pointer to function that takes two parameter returning int 
```