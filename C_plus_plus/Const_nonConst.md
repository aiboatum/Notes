#   const and nonconst
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