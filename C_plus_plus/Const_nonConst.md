#   const and nonconst
1. `const object must be initialized explicitly`
   ```c++
   const int a;// error
   const int b=3;// ok
   int main(){
        const int a;// error
        b=4;// error, b is a const type
   }
   ```
2. `const object will be initialized at the run time and complie time`
   ```c++
   const int i=get_size();// ok, intialized at run time
   const int i=1024;// ok, initialized at complie time
   ```
3. `in order to avoid the conflict the same names of const objects arise, therefore const objects are defined as local to the source file, which means that it is avaliable only in this source file.`
4. `using the extern, we can use a const object in different source files.`
```c++
//file_1.cpp 
//define and initializes a const object that is accessible to other files
//the extern keywords tells the complier the bufsize may be used in ohter source files.
extern const int bufsize=3;


//file_2.cpp
//all of file_1.cpp and file_2.cpp should be complied. 
extern int bufsize;

___________________________________________________
//also, we can do that by puting extern objects into one header files.
// header.h
extern int bufsize;
extern std::string s;
...
//file_1.cpp
extern int bufsize=3;
extern std::string s="123";

//file_2.cpp
#include "header.h"
int main(){
    std::cout<<s<<std::endl;
    std::cout<<bufsize<<std::endl;
}
```
**demo**
```c++
#include <iostream>
#include <string>
int main() {
    int a = 2; // a is a nonconst type.
	const int b = 3;    // b is a const type.

	int *q1=&a; // ok, pointer to nonconst type (namely, int type) can point to nonconst type.
    
        // a pointer to const type can also point to a nonconst type.
        const int *q2=&b;
        q2 = q1;    // equivalent to q2=&a;

        int **p1=&q1; // ok
        
        //p2 is a pointer to a pointer to const int.
	const int **p2=&q2;
        *p2 = *p1;//(*p2) is a pointer to const int, (*p1) is a pointer to int.

        // p2 = p1;
	// p2 is a pointer to a pointer to const int, p1 is a pointer to pointer to int.
	// which means that *p2 is a pointer to const int, and then *p2 cannot change
	// the value it points, if "p2=p1" is legal, then p2 is a pointer to pointer to 
	// int. *p2 is a pointer to int, and thereby *p2 can change the value it points.
	
        typedef int* pint;//pint is a type of int *.
	const pint *p3;
        // p3 is a pointer to const pointer to int.
	// equivalent to const int **p3 ?
	// equivalent to const (int *) *p3 (it is illegal.);
	// the differency among const int **p and const (int *) p.

        #define pint int *
        const pint *p;// equivalent to const int **p;
	p3 = p1;
	decltype(p3) p4 = p1;
	using pintt = int *;
	const pintt *p = p1;
	std::cout << **p3<<" "<<**p4;
	return 0;
}
```
**reference to const**
1. `reference is implicit const. Since the reference must be initialized, and cnanot bind anoter object.`
2. `nonconst reference cannot bind a const object`
```c++
const int ci=1024;
const int &r1=ci; // ok

r1=43;// ri reference a const object
int &r2=ci;//error

int ci=1024;
const int &r=ci;// ok,a reference to const can bind a nonconst object.

const int &r=1024;// 
const int i=3;
const int &r=i*2;// ok, i*2 is const expersion
```