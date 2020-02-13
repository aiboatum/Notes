#   constexpr
constexpr is one of keywords introduced in Standard c++11. It refers to const expresion, **which can be evaluated in the compile time.** Such as, the resutls returned by sizeof, the literals(integer literal,string literal,etc.), the address of the gobal functions/variables and so forth.

A const object intialized by const expresion(such as ,literals) is a const expression. Whether a object(expresion) is constexpr or not depends on its type and intializations.
```c++
const int max_files=20;// max_files is a constexpr.
const int limit_=max_files+1;// limit_ is a constexpr.

int run_time_size=23;// it is not a constexpr.
const int run_get_size=getSize();// getSize() is a run-time function.


// In c++11, we can do this explicitly.
constexpr int mf=20;
constexpr int limit=mf+2;
constexpr int sz=size();// sz is a constexpr if size is a constexpr function.

```
**some difference among const and constexpr**
```c++
const int *p=nullptr;// p is a pointer to a const int object.
constexpr int *q=nullptr;//constexpr indicates that (int *q) is a const expresion, which means that q is a const pointer to int. The pointer q cannot change the object it points to.

constexpr const int *p=nullptr;// p is a const pointer to a const int.
```

constexpr expressions will be expanded in line at the compile time. Thus, constexpr expressions are the implicit inline.

**demo**
```c++
constexpr int foo(int i){//or , constexpr int foo(const int i)
    return i+1;
}
constexpr int i=2;// or, const int i=2;
constexpr int a=foo(2);//ok, foo is a constexpr function.
constexpr int a=foo(i);//ok, i is a constexpr object.
constexpr int a=foo(i*2+1);//ok, i*2 +1 is a constexpr expression.

constexpr int a=getSize();//error, getSize is a run-time funciton.
```
- constexpr functions
constexpr functions will be restricted by the following rules:
1. constexpr functions only have one return statement.
2. the return type must be literals.
3. the parameter must be literals.
```c++
constexpr int new_sz(){return 42;}//ok
constexpr int foo=new_sz();
```
**constexpr constructor**
`//pass`
