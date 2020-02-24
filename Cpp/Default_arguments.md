#   Default Arguments
- **Declaration functions with the default arguments**
  - 
  ```cpp
    #include <iostream>

    int foo(int a=3,int b=3);   //declaration
    int foo(int =3,int =3); //equivalent to the declaration above
    int foo(int ,int =3);   //ok, the second parameter has the default argument 3.

    //  NOTE: if a parameter has a default argument, all the parameters that follow it must have default arguments.

    int foo(int =3,int );//error, default arguments missing for parameter 2. Because if we do so, the call of this function is ambiguous.
    //foo(2) = foo(3,2)?
    //foo(2) = foo(2,unknown number)?

    int foo(int a,int b){   //definition
        return a+b;
    }
    

    int main(){
        std::cout<<foo(3,5)<<std::endl; //return 3+5
        std::cout<<foo()<<std::endl;    //return  3+3
    }
    ```

- Default Argument Declarations
  - 
  ```cpp
  int foo(int =3,int b);//error
  
  //it is legal to redeclare a function multiple times.
  //however, each parameter can have its default specified only once in a given scope,
  //thus, any sebsequent declaration can add a default only for a parameter that has not previously had a default specified.
  //also,defaults can be specified only if all parameters to the right alreaddy have defaults.
  //e.g.:
  int foo(int ,int ,int);
  int foo(int ,int =3,int);//error
  int foo(int ,int ,int =3);
  int foo(int ,int =3,int );
  int foo(int =3,int ,int );

  //the following statement is equivalent to the three statements above
  int foo(int =3,int =3,int =3);

  ```
- Default Arguments Initializers
  - 
  ```cpp
  //Local variable may not be used as a default arguments.
    #include <iostream>
    int a=3;
    int b=3;
    int c=3;
    int foo(int =a,int =b,int =c);
    int foo(int parameter_1,int parameter_2,int parameter_3){
        return parameter_1+parameter_2+parameter_3;
    }
    int bar(){
        a=4;
        int b=4;    // hides the outer definition of b but does not change the default
        c=4;
        return foo();
    }
    int main()
    {
        std::cout<<foo()<<'\n';//9,that is foo(3,3,3)
        std::cout<<bar()<<'\n';//11,that is foo(4,3,4);
        return 0;
    }
  ```