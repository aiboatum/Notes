# 全文本搜索

全文本搜索需要
- 引擎支持
- 建立索引（DBMS或引擎）负责

为指定字段创建索引，那文本搜索的性能是最佳的。

## 使用全文本搜索

上面提到，必须要建立相应的索引。因此，一旦数据发生改变，就需要重新建立索引。当然这些工作由DBMS负责。

### 启用全文本搜索支持

一般在建立表时，就定义是否索引某个字段。如，
```sql
create table productnotes(
    note_id int NOT NULL auto_increment,
    prod_id char(10) NOT NULL,
    note_date datetime NOT NULL,
    note_text text NULL,
    PRIMARY KEY(note_id),   -- 指定主键
    FULLTEXT(note_text)     -- 指定索引哪些字段（列）
)ENGINE=MyISAM; -- 指定引擎
```
定义之后，MySQL会负责索引的相同工作。

> 应注意：索引建立是需要时间的。因此导入数据的时候，应当在数据的导入完全之后再进行索引。这样效率会更高，要不，导入一点更新一次索引，效率十分之慢。

### 进行全文本搜索

在索引之后，使用两个函数Match()和Against()执行全文本搜索，其中Match指定被搜索的字段（列），Against指定要使用的搜索表达式。如，
```sql
select note_text from productnotes
where Match(note_text) Against('rabbit');
-- 同样可以用where完成
select note_text from productnotes where note_text LIKE '%rabbit%';
```
其中，Match中的字段必须要和FULLTEXT中相同，即必须建立了索引。该搜索不区分大小写，除非使用BINARY方式。

还有其他的，如扩展和布尔模式等知识，略过。