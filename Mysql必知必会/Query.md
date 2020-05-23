# 查询 (query)

select语句是SQL的查询，并且任何SQL语句都是查询。

查询可以嵌套。我们使用select检索的结果，可以作为另一个select的检索依据。例子如下：

```sql
select order_num from orderitems where prod_id='TNT2';

select cust_id from orders where order_num in (20005,20007);

-- 嵌套如下

select cust_id from orders
where order_num IN (select order_num from orderitems where prod_id='TNT2');

```

嵌套的select语句是由内相外处理。以上SQL语句首先，执行
```sql
select order_num from orderitems where prod_id='TNT2';
```
该插叙返回的order_num：20005，20007。这俩值以IN操作符要求的逗号分隔的格式传递给外部的where子句。外部查询变成：
```sql
select cust_id from orders where order_num in (20005,20007);
```
该查询最终返回两个cust_id：10001，10004。当然该结果依然可以被嵌套，如下：
```sql
select cust_name,cust_contact from customers
where cust_id in (select cust_id from orders 
                    where order_nums in (
                        select order_num from orderitems
                        where prod_id ='TNT2'
                    )
                );
```
