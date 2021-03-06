# 2PL

TwoPhase Locking，2PL 协议，即两段锁协议，是指所有的事务必须分为两个阶段对数据项进行加锁和解锁。

* 在对任何数据 R/W 操作之前，首先在申请并获得该数据的封锁 （locking）
* 在释放一个封锁之后，事务不再申请和获得任何其他封锁。 

也就是说，事务划分为两个阶段：
1. Expanding phase: 申请获得封锁，并且不能释放任何锁
2. Shrinking phase: 释放锁，不能再申请任何锁

## 为什么 2PL 是可以串行化的充分条件？

**证明**：以两个并发事务 T1 和 T2 为例（多个并发事务类推即可）。根据可串性化定义可知，事务不可串性化只可能发生在下列两种情况：
* 事务 T1 写 同时 T2 读/写某一对象 A （==情况1==）
* 事务 T1 读/写 同时 T2 写某一对象 A （==情况2==）

> 可以看到，这是<font color="blue">冲突操作的定义</font>。不存在冲突操作的并发一定满足一致性的。

我们可以将 A 称为 **潜在冲突对象**。设 T1 和 T2 访问的潜在冲突的公共对象为 {A1,A2,...,An}。不失一般性，假设这组潜在冲突对象中 X={A1,A2,...,Ai} 均符合==情况1==。Y={Ai+1,...,An} 符合==情况2==。

对于 ∀ x ∈ X，
* T1 需要 Xlock （==操作1==）
* T2 需要 Xlock 或 Slock （==操作2==）

1. 如果操作1先执行，则 T1 获得锁，T2 等待

由于遵守两段锁协议，T1 成功获得 x 和 Y 中全部对象以及 **非潜在冲突对象** 的锁后，才会释放锁（类似于数据库中死锁预防的一次封锁法，但不等同）。

这时如果存在 w ∈ x 或 Y，T2 已获得 w 的锁，则出现死锁。否则，T1 在对 x 和 Y 中的对象全部处理完毕后，T2 才能执行。这就相当于按 T1、T2 的顺序串行执行。

2. 操作2先执行的情况类似。

因此，当并发事务遵守 2PL 协议时，<font color="blue">在不发生死锁的情况下</font>，对这些事务的并发调度一定是可串行的。

证毕。