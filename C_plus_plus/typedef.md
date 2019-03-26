# typedef sepcifier
- typedef is one of type alias
```c++
//we can use the keywords typedef as the following shows.
typedef double wages;// wages is equivalent to double
typedef wages  base,* p;// base is synonymous with double , p is synonymous with duoble *.
typedef struct list LIST;// LIST is equivalent to struct list;


```
```c++
bool bar(const int &,const int &);
// foo1 and foo2 have the function type.
typedef bool foo1(const int &,const int &);// foo1 is a function taking two parameters and returning bool.
typedef decltype(bar) foo2;// foo2 has the equivalent type to foo1.

// foo1 and foo2 have pointer to function type
typedef bool (*foo1)(const int &,const int &);
typedef decltype(bar) *foo2;
```