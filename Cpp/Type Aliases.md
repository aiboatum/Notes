# Type Alias
> In order to simplify the program coding in terms of types, e.g. some types are too long to remember and write, or are so complicated to spell.

Type aliases are names that is synonyms for another types.

## Define a type alias
```c++
typedef int Tint;
typedef long double Tld;
typedef Tint *p;// p is a synonym for int *

using Tint=int;
using T=std::vector<int>;
T a(1);// a is a vector, which consists of one element, i.e.,1
```

**Note**
```c++
using char *pstring;// pstrings is type of char *;
const pstring cstr=0;// cstr is a constant pointer to char
// pstring is a pointer (a pointer to char). 
// So, const pstring is a constant pointer to char--not a pointer to const char.
```
