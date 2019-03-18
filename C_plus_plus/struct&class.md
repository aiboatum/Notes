#   结构体与类
- c中结构体内不可以直接定义函数
- c中的结构体不可以初始化
- c++均可以
  C中使用结构体实现面向对象中的method().
  ```c++
    #include <iostream>
    #include <string>
    //using namespace std;
    class HELLO{
        public:void sayHello(string name){
            std::cout<<"Hello!"<<name<<std::endl;
        }
    };


    int main(){
        HELLO *hello=new HELLO();
        hello->sayHello("a");
        delete(hello);
        return 0;
    }

    //现在使用纯C实现上面的功能
    #include <stdio.h>
    #include <stdlib.h>// malloc
    struct HELLO{
        void *sayHello(char *name);
    };
    void sayHello(char *name){
        printf("Hello ,%s\n",name);
    }
    int main(){
        struct HELLO *hello=(struct HELLO *)malloc(sizeof(*hello));
            hello->sayHello=sayHello;//结构体内的每个函数都需要指向对应的函数地址（函数名即可）
        hello->sayHello("a");
        free(hello);
        return 0;
    }
    //当然C++中类外定义函数就和C风格很像了
    #include <iostream>
    #include <string>
    class HELLO{
        public:void sayHello(string name);
    };
    void HELLO::sayHello(string name){
        std::cout<<"Hello!"<<name<<std::endl;
    }
    int main(){
        HELLO *hello=new HELLO();
        hello->sayHello("a");
        delete(hello);
        return 0;
    }
  ```