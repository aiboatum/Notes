# Predicate
**We can review the definition from wikipedia:**
> - In mathematics, a **predicate** is commonly understood to be a Boolean-valued function: ***X***->{TRUE,FALSE}, called the predicate on ***X***. 
> - In computer programming, an **assertion** is a statement that a predicate (Boolean-valued function, a ture-false expression) is expected to always be true at that point in the code. If an assertion evaluates to false at run time,an assertion failure results, which typically causes the program to crash, or to throw an assertion exception. 

An example is given here.

$x\geq 3$ is a predicate (varing from the different value of $x$).

In c++, a predicate is an expression that can be called and that returns a value that can be used as a condition. (i.e., a Boolean-valued function).

1. unary predicates: they have a single parameter, e.g. $x$
2. binary predicates: they have two parameters, e.g. $x>y$.

## Using predicates to customize our own version of sort

```c++
bool mycmp(const int &a,const int &b){
    return a<b;// this means a is sorted to follow b and vice verse.
}

sort(vec.begin(),vec.end(),mycmp);// vec is a vector composed of int
```