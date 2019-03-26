#  Class
- *Class(keywords class and keywords sturct)*
  - **定义在class内的函数隐含为inline函数**
  - *this指针和c中的struct有关系*
- **this pointer**
```c++
std::string isbn() const {return bookNO;}

//equivalent to 
std::string isbn() const {return this->bookNO;}
std::string isbn() const{return (*this).bookNO;}

total.isbn();
Sales_data::isbn(&total);
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