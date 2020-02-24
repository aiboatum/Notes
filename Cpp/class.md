#  Class
- *Class(keywords class and keywords sturct)*.
> The only difference between using class and using struct to define a class is the default access level.
  - **Functions defined in the class are inline implicitly**

## Introduce $this$ pointer
```c++
std::string isbn() const {return bookNO;}

//equivalent to 
std::string isbn() const {return this->bookNO;}
std::string isbn() const{return (*this).bookNO;}

total.isbn();   //total is a Sales_data object.
Sales_data::isbn(&total);
// the compiler pass the address of Sales_date object
// to the implicit 'this' parameter in isbn().
// when we create a object of some classes, 
// 'this' pointer points to this object, 
// that is taking the address of this object.
```
---

# Introduce const Menber functions
```c++
std::string isbn() const{return bookNO};
```
what's the purpose of the const?
  1. In c++, the `this` pointer is a const pointer. And `this` pointer points to the `nonconst` version of the class type. Hence, `this` pointer cannot point to a `const` object, that is, we connot bind `this` to a `const` object.
  2. The purpose of that `const` is to modify the type of the the implicit `this` pointer, hence we can call this member function on a `const` object. The type of `this` will be `const Sales_data * const.`
  4. A `const` following the parameter list indicates that `this` is a pointer to `const`.

```c++
// pseudo-code illustration of how the implicit 'this' pointer is used
// the following code is illegal: we may not explicitly define the this pointer ourselves.
std::string Sales_data::isbn(const Sales_data *const this){
    return this->bookN;
}
```
---
> The fact that `this` is a pointer to `const` means that `const` member functions connot change the object on which they are called.

- class scope and member funcitons
- **define a menber function outside the class**
  1. the member functions defined outside the class will be treated as inside the scope of the classes.
  2. the member functions defined outside the class can use the data members of class.(the reason is explained in 1)
- define a function to return `this` object
```c++
// 'this' pointer binds to the object on which the member functions is called.
Sales_date & Sales_data::combine(const Sales_data * s){
    this->a+=s->a;
    this->b+=s->b;
    return *this;//return the object on which the function was called
}
Sales_data s1();
Sales_data s2();
s1.combine(s2);// return s1
s2.conbine(s1);// return s2
```
---
- **defining nonmember class-related functions**
  - ***Ordinarily, nonmember functions that are part of the interface of a class should be declared in the same header as the class itself.***

An simple example is given here.
```c++
Sales_data add(const Sales_data &a,const Sales_data &b){
    // do something with a and b
    // return a reference of Type Sales_data 
}
```

---
- **demo**
```c++
class A{
    public:
        int price;
        void SetPrince(int p);
};
void A::SetPrice(int p){
    price=p;
}
int main(void){
    A a=A();
    a.SetPrice(34);
}
// In the early time, c++ source code will be converted to c source code, because of having no compilers for c++.
struct A{
    //There exsits difference among struct in c and in c++.
    //Functions cannot be defined in c, but which can be done in c++.
    //Also, the pointers to functions can be defined in c. 
    int price;
};
void SetPrice(struct A * this, int p){
    this->price=p;
}
int main(){
    ...
    SetPrice(&A,34);
    return 0;
}

// the another equivalent form
struct A{
    int price;
    void (*SetPrice)(struct A *this, int price);
};
void SetPrice(struct A * this, int p){
    this->price=p;
}
int main(){
    struct A * a=(struct A *)malloc(sizeof(*a));
    a->SetPrice=SetPrice;   // the pointer SetPrice points to function SetPrice
    a->SetPrice(a,34);
    return 0;
}
```
---
- **constructors**
    1. `constructors is a special member functions to initialize the data menbers of a class object`
    2. `constructors can be defined as more than one form, that is, a class can have multiple constructors to initialize objects differently. these different constructors can be viewed as overload functions`
    3. `constructor will be excuted when an object of a class type is created`
    4. `constructor has no return type`
- **constructor initializer list**
    ```c++
    Sales_data(const std::string &s):bookNo(s){}
    Sales_data(const std::string &s,unsigned n,double p)
        :bookNo(s),units_sold(n),revenue(p*n){}
    ```
- ***When a member is omitted from the constructor initializer list,it is implicitly initialized using the same process as is used by the synthesized default constructor***
    ```c++
    //like this:
    Sales_data(const std::string &s):
        bookNo(s),units_sold(0),revenue(0){}
    
    Sales_data():
        bookNo(""),units_sold(0),revenue(0){}

    //demo:
    #include <string>
    #include <iostream>
    class myclass{
    public:
        int a;
        int b;
        std::string s;
    public:
        //constructor
        myclass():a(2),b(3),s("2+3"){}
        myclass(int a,int b):a(a),b(b),s(""){}
    };
    int main()
    {
    myclass m1=myclass();
    // output 2 3 2+3
    std::cout<<m1.a<<" "<<m1.b<<" "<<m1.s<<std::endl;
    }
    ```    
---
- **friends**
  1. `A class can allow another class or function to access its nonpublic members by making that class or function a friend.`
  2. `friend declarations for nonmember functions should be added to the body of class` 
  3. `friends declarations may appear only inside a class definition, they can appear anywhere in the class.`
  4. `friends are not members of the class`
  5. `friends are not affected by the access control of the public or private`
  6. `firend declaration only specifies access`
  7. `firend functions or class cannot directly access the nonpublic members, which can be accessed by object (in which declares friends functions or class)`
- *demo*
    ```c++
    class A{
        private:
            int a;
        public:
            A()=default;
            friend void foo();// just indicate that the function foo is a friend to Class A
            friend void bar();
    };
    //outside the class should have the declaration of functions
    void foo();
    int main(){
        foo();//ok
        bar();//error
    }
    ```
    8. `the definition of friends can be inside or outside body of a class`
        ```c++
        #include <iostream>
        #include <string>
        #include <vector>
        class A {
        private:
            int a = 123;
        public:
            A() = default;
            A(int a) :a(a) {}
            void bar() {
                std::cout << a.a;// ok, private variable can be accessed by its member functions inside the class.
            }
            friend void foo(A &a) {
                // ok, foo() is a friend function, and is thereby able to access its unpublic member of class A.
                std::cout << a.a;
                // this function can also be defined outside the class.
            }
        };
        void foo(); // there must exists this declaration ouside class. otherwise, the call to foo() will be failed.
        int main()
        {	foo();
            bar();
        }  
        ```
> remark:the friend function or class can access the non-public members of class, which means that the private members of class can be accessed through the object of the class. Objects of a class can generally only access non-public objects through member functions of the class.

---
- **Class declarations**
    1. `class A;// this is a declaration of the A class, but not initialized. The type A is an incomplete type, that is, we do not know the members that A contians. Moreover, it is better to not make such declarations.`
    ```c++
    //  we can define pointers or reference to such type (incomplete type), 
    //  thus we can declare (but not define) functions that use an imcomplete type as a parameter or return type. Which seems to relative to C.
    struct List{
        int a;
        struct List *pre;   //ok
        struct List next;   //error
    };
    ```
    2. `class A can be data members of class B only if the B has been defined (not only declaration), which means that the clas A is a complete type and  the complier knows the storage that the A needs.`