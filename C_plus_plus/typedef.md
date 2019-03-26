# typedef sepcifier
```c++
bool bar(const int &,const int &);
// foo1 and foo2 have the function type.
typedef bool foo1(const int &,const int &);// foo1 is a function taking two parameters and returning bool.
typedef decltype(bar) foo2;// foo2 has the equivalent type to foo1.

// foo1 and foo2 have pointer to function type
typedef bool (*foo1)(const int &,const int &);
typedef decltype(bar) *foo2;
```