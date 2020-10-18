> [陈硕-csdn-为什么多线程读写shared_ptr要加锁？](https://blog.csdn.net/Solstice/article/details/8547547)

# shread_ptr 的数据结构

`shread_ptr` 的实现基本都是采用在 heap 上放个 count 的方法【理论上，还可以使用循环链表的方法，但是没有实例】。

`shared_ptr` 包含两个数据成员：一个是指向 `T` 的指针 `ptr` ，另一个是 `ref_count` 指针，指向堆上的 `ref_count` 对象。

## ref_count 对象

`ref_count` 对象有多个成员，大致有如下几个：

* `vptr`
* `use_count`
* `weak_count`
* `ptr` : 指向其管理的对象

以下两个是可选项

* deleter
* allocator
