#   static class members
static class members are associated with the class, rather than with individual objects of the class type. Which means that all objects of the same class hold the static object commonly. The static objects will be stored only once, and be not destoryed until the program terminates.

**declaring static members and using a class static member**
```c++
class myclass{
    private:
    //
    public:
        myclass()=default;
        static void show_static();//declaration of static objects
        void show_nonstatic();
};
int main(){
    // we can access a static member directly through the scope operator.
    myclass::show_static();//ok
    show_static();//error
    myclass m1;
    m1.show_nonstatic();//ok
}
```
**Note:**
static class member cannot call the nonstatic class member directly.
```c++
class myclass{
    private:
    //...
    std::string s="123";
    public:
        myclass()=default;
        static void show_static(){
            show_nonstatic();//error
            myclass m1;
            m1.show_nonstatic();//ok

            //static members can access the private data members directly.
            std::cout<<this->s;//ok
        }
        //or, like this
        static void show_static(myclass &m){
            m.show_nonstatic();
        }
        void show_nonstatic();
};
// we can also define the static member function
// the keyword static will be removed and add the scope opreator into the definition.
void myclass::show_static(){
    //...
}
```
***Because static data members are not part of individual objects of the class type, they are not defined when we create objects of the class. Thus, they are not initialized by the class constructors.***