[TOC]
# Copy Control and Resource Management

Ordinarily, classes that manage resources that do not reside in the class must define the copy-control members (copy constructor,move constructor,copy-assignment,destructor).For example, the data member allocated by `new`. 

In order to define these members, we first have to decide what copying an object of our type wil mean. In general, we have two choices: We can define the copy operations to make the class behave like a value or like a pointer.

- Behave like values: when we copy a valuelike object, the copy and the original are independent of each ohter. Changes made to the copy have no effect on the original, and vice verse.(e.g. `string`)
- Behave like pointers:when we copy such objects, the copy and the original use the same underlying data. Changes made to the copy also change the original, and vice verse.(e.g., `shared_ptr`)

To provide valuelike behaviour, each object has to have its own copy of the resource that the class manages. That means each `HasPtr` object must have its own copy of the `string` to which `ps` points. To implement valuelike behavior `HasPtr` needs:
```c++
class HasPtr{
private:
	int val;
	string* ps;
public:
	HasPtr(const string &s=string()):ps(new string(s)),val(0){}
	HasPtr(const HasPtr &p):ps(new string(*p.ps)),val(p.val){}
	HasPtr& operator=(const HasPtr& p) {
        auto newps=new string(*p.ps);
        this->val=p.val;
        delete ps;
		this->ps = newps;
		return *this;
	};
	~HasPtr() { delete ps; }
};
```