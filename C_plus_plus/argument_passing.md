#   argument passing
  * *passed by reference*
  * *passed by value*
##  passing arguments by value
  - *when we initialize a nonreference type variable, the value of the initializer is copied*.
  - *when the argument value is copied, the parameter and argument are indepent objects*
  - pointer parameters
    - when we copy a pointer, the value of the pointer is copied. After the copy, the two pointers are distinct.
##  passing arguments by reference
  - *as with any other reference, a reference parameter is bound directly to the object from which it is initialized*
  - **Using references to avoid copies**
    -  *it can be inefficient to copy objects of large class type or large containers.*
    -  *moreover, some class types cannot be copied*
    -  *also, the reference parameter should be references to const*