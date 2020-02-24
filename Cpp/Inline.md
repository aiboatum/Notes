#   Inline functions
- **inline functions avoid function all overhead**.
  -  *A function specified as inline is expanded "inline" at each call.* 
  -  inline和c中的macro作用类似，但是其机制不同
    - c style 
    ```c
    #define abs(x) ((x)>0)?(x):(-x)
    ```

```c++
    #include <string>
    #include <iostream>

    inline const std::string & shorterString(const std::string &s1,const std::string &s2){
        return s1.size()<s2.size()?s1:s2;
    }
    inline int abs(int x){
        return x>0?x:-x;
    }

    int main(void){
        std::string s1("s1 string");
        std::string s2("s2  string");
        std::cout<<shorterString(s1,s2)<<std::endl;
        std::cout<<abs(-3);
    }
```
