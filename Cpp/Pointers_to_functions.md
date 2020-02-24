#   Pointers to functions
- **declaration of pointers pointing to functions**
  - *the declaration in c++ is same as the one in c*
```c++
// from c++ primer
bool lengthCompare(const std::string &,const std::string &);

// pr is a pointer pointing to a function with two parameters returning bool type.
// and pr is uninitialized.
bool (*pr)(const std::string &,const std::string &);

// note that:
// pr is a function returning a bool * type and taking two parameters.
bool *pr(const std::string &,const std::string &);
```
- **using function pointers**
```c++
pf = lengthCompare;   // ok, the pr points to the function named lengthCompare.
pf = &lengthCompare;    // ok, equivalent to the above satement.

// Also, we can use a pointer to a function to call the function to which the pointer points.
// the three satements in the following is equivalent to each other.
bool b1 = pf("hello","goodbye");
bool b2 = (*pf)("hello","goodbye");
bool b3 = lengthCompare("hello","goodbye");

```
- **pointers to overload functions**
```c++
void foo(int *);
void foo(unsigned int);
void (*pf1)=(unsigned int)=foo;// pf1 points to foo(unsigned int)

//**the type of the pointer must match one of the overload functions exactly.**
void (*pf2)(int)=foo;//error, no foo with a matching parameter list.
double (*pf3)(int *)=foo;//error, return type of foo and pf3 don't match.

```
- **function pointer parameter**
```c++
// in terms of array, we can declare the following 
void foo(int []array);
void foo(int array[]);
//the above two statements will be converted to this.
void foo(int *array);

//hence, we can write a parameter that looks like a function type, but it will be treated as a pointer.

void bar(const std::string &s1,const std::string &s2,bool pf(const std::string &,const std::string &));
//equivalent declaration : explicitly define the parameter as a pointer to function
void bar(const std::string &s1,const std::string &s2,bool (*pf)(const std::string &,const std::string &));

//call the function bar
bar(s1,s2,lengthCompare);
```
- **return pointer to function**
```c++
// 'using'
using F=int(int *,int);
using pF=int(*)(int *,int); 
//pF is pointer to function

pF f1(int);// ok
F f1(int);// error, cannot return the funtion type
F * f1(int);// ok, explicitly declare the returning type


// Also, we can write it as the form
// f1 is function that takes one parameter returning pointer type, which points to function that takes two parameters and returns int type. 
int (*f1(int))(int *,int);
// but this declaration is so complicated.
// we apply the auto to declare this.
auto f1(int)->int(*)(int *,int);

// use the decltype 
string::size_type sumLenth(const std::string &,const std::string &);

decltype(sumLenth) *getFoo(const std::string &);

```
- **example**
```c++
#include <iostream>
#include <vector>
int foo(int,int);
int foo(int a,int b){
    return a+b;
}
int bar(int,int);
int bar(int  a,int b){
    return a-b;
}
int main(){
    using f=int(*)(int,int);
    std::vector <f> ret;
    decltype(foo) *p1=foo;
    decltype(foo) *p2=&bar;
    ret.push_back(p1);
    ret.push_back(p2);
    std::cout<<ret[0](1,2)<<std::endl;
    std::cout<<(*ret[1])(1,2)<<std::endl;
}
```
