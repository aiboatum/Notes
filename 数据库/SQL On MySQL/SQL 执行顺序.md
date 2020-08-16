# SQL 语句执行顺序 

现有以下 SQL 语句，定义如下：

~~~sql
(1) SELECT (2)DISTINCT<select_list>
(3) FROM <left_table>
(4) <join_type> JOIN <right_table>
(5)         ON <join_condition>
(6) WHERE <where_condition>
(7) GROUP BY <group_by_list>
(8) WITH {CUBE|ROLLUP}
(9) HAVING <having_condition>
(10) ORDER BY <order_by_condition>
(11) LIMIT <limit_number>
~~~

其执行顺序如下：
~~~sql
(8) SELECT (9)DISTINCT<select_list>
(1) FROM <left_table>
(3) <join_type> JOIN <right_table>
(2)         ON <join_condition>
(4) WHERE <where_condition>
(5) GROUP BY <group_by_list>
(6) WITH {CUBE|ROLLUP}
(7) HAVING <having_condition>
(10) ORDER BY <order_by_list>
(11) LIMIT <limit_number>
~~~

详细顺序如下：

- (1) `FROM`: 对 `FROM` 子句中的左表 `<left_table>` 和右表 `<right_table>` 执行笛卡儿积，产生虚拟表 VT1;

- (2) `ON`: 对虚拟表 VT1 进行 `ON` 筛选，只有那些符合 `<join_condition>` 的行才被插入虚拟表 VT2;

- (3) `JOIN`: 如果指定了 `OUTER JOIN` (如 `LEFT OUTER JOIN`、`RIGHT OUTER JOIN`)，那么保留表中未匹配的行作为外部行添加到虚拟表 VT2，产生虚拟表 VT3。如果 `FROM` 子句包含两个以上的表，则对上一个连接生成的结果表 VT3 和下一个表重复执行步骤1\~步骤3，直到处理完所有的表;

- (4) `WHERE`: 对虚拟表 VT3 应用 `WHERE` 过滤条件，只有符合 `<where_condition>` 的记录才会被插入虚拟表 VT4;

- (5) `GROUP By`: 根据 `GROUP BY` 子句中的列，对 VT4 中的记录进行分组操作，产生 VT5;

- (6) `CUBE|ROllUP`: 对 VT5 进行 `CUBE` 或 `ROLLUP` 操作，产生表 VT6;

- (7) `HAVING`: 对虚拟表 VT6 应用 `HAVING` 过滤器，只有符合 `<having_condition>` 的记录才会被插入到 VT7;

- (8) `SELECT`: 第二次执行 `SELECT` 操作，选择指定的列，插入到虚拟表 VT8 中;

- (9) `DISTINCT`: 去除重复，产生虚拟表 VT9;

- (10) `ORDER BY`: 将虚拟表 VT9 中的记录按照 `<order_by_list>` 进行排序操作，产生虚拟表 VT10;

- (11) `LIMIT`: 取出指定街行的记录，产生虚拟表 VT11，并返回给查询用户