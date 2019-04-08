# Class scope

the concept of scope in c and c++ is very important.

```c++
typedef double Money;
std::string bal;
std::string bal_s="empty string";
class Account{
    public:
    Money balance(){
        return bal; // this bal is Money type, not string type.
    }
    std::string foo(){
        return bal_s;// return the outer string object
    }
    private:
    Money bal=1.23;
}
// Because function bodies are compiled only after the entire class has been seen, the return inside balance function returns the member named bal, not the string from the outer scope.
int main(){
    Account a=Accoun();
    std::cout<<a.balance();// output 1.23
    std::cout<<a.foo();// output "empty strin"
}
```