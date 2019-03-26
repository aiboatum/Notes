#  Class
- *Class(keywords class and keywords sturct)*
  - **Functions defined in the class are inline implicitly**
- **introducing $this$ pointer**
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
- **Introducing const Menber functions**
```c++
std::string isbn() const{return bookNO};
```
what's the purpose of the const?
>1. In c++, the $this$ pointer is a const pointer.
And $this$ pointer points to the nonconst version of the class type. Hence, $this$ pointer cannot point to a const object, **that is, we connot bind ${this}$ to a $const$ object.**
>2. by default, the type of this pointer is Sales_data *$const$.
>3. **the purpose of that const is to modify the type of the the implicit $this$ pointer**, hence we can call this member function on a $const$ object. The type of $this$ will be $const$ Sales_data * $const$.
>4. **A $const$ following the parameter list indicates that $this$ is a pointer to $const$.**
```c++
// pseudo-code illustration of how the implicit 'this' pointer is used
// the following code is illegal: we may not explicitly define the this pointer ourselves.
std::string Sales_date::isbn(const Sales_data *const this){
    return this->bookNO;
}
```

**the fact that $this$ is a pointer to $const$ means that $const$ member functions connot change the object on which they are called.** 
- *class scope and member funcitons*
- **define a menber function outside the class**
  1. the member functions defined outside the class will be treated as inside the scope of the classes.
  2. the member functions defined outside the class can use the data members of class.(the reason is explained in 1)
- **define a function to return 'this' object**
```c++
// 'this' pointer binds to the object on which the member functions is called.
Sales_date & Sales_date::combine(const Sales_data * s){
    this->a+=s->a;
    this->b+=s->b;
    return *this;//return the object on which the function was called
}
Sales_date s1();
Sales_date s2();
s1.combine(s2);// return s1
s2.conbine(s1);// return s2
```

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
        return 0;
    }
    
    //早起缺少c++编译器，需转换为c
    struct A{
        int price;
            //C中的struct和c++中有区别，c中的不可以定义函数（可以定义函数指针）
    };
    void SetPrice(struct A * this, int p){
        this->price=p;
    }
    int main(){
        ...
        SetPrice(&A,34);
        return 0;
    }
    //或者可以写成这样
    struct A{
        int price;
            //C中的struct和c++中有区别，c中的不可以定义函数（可以定义函数指针）
        void * SetPrice(struct A *this, int price);
        // SetPrice 是一个函数指针，指向一个void返回型的函数
    };
    void SetPrice(struct A * this, int p){
        this->price=p;
    }
    int main(){
        struct A * a=(struct A *)malloc(sizeof(*a));
        a->SetPrice=SetPrice;
        a->SetPrice(a,34);
        return 0;
    }
    ```