# 组合查询

MySQL 允许多个查询进行并集返回。组合查询被称为**并**（union）或复合查询（compound query）。

> 组合查询与多个 `where` 条件，思考一下便会发现组合相同表的多个查询操作，可以通过多个 `where` 完成。

## 创见组合查询

使用 `UNION` 操作符。

### 使用UNION

很简单：
```sql
-- 第一条，检索prod_price小于等于5
select vend_id,prod_id,prod_price from products where prod_price<=5;
-- 第二条
select vend_id,prod_id,prod_price from products where vend_id IN (1001,1002);
-- 使用union
select vend_id,prod_id,prod_price from products where prod_price<=5
union
select vend_id,prod_id,prod_price from products where vend_id IN (1001,1002);

-- 等价的多个where形式
select vend_id,prod_id,prod_price from products where prod_price<=5 or vend_id IN (1001,1002);
```

### UNION 规则

`union` 用法很简单，但有些规则需要注意：
- `union` 必须由两条以上的 `select` 语句。
- `union` 的每个查询必须包含相同数量的相同字段（字段、计算字段等）。
- 字段类型必须兼容

### 包含或取消重复的行

上面的 `union` 例子，第一个 `select` 返回 4 个记录（行），第二条返回 5 行。但是 `union` 之后只返回了 8 个，而不是 9 个。其实，很容易想到，并操作是自动消除重复的记录的，和数学（离散数学）中是内在统一的。

以上 `union` 是默认去除重复的行，也可以通过 `union all` 返回所有匹配行。

### UNION 中的 ORDER BY

`order by` 是对返回结果进行排序，很显然多个 `union` 中 `order by` 总是在最后。**多个order by**很显然是个错误的行为。