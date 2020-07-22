# 第3章 SQL语句
## 3.1 SQL概述
### SQL语句分类
数据定义语言：简称DDL(Data Definition Language)，用来定义数据库对象：数据库，表，列等。关键字：create，alter，drop等
数据操作语言：简称DML(Data Manipulation Language)，用来对数据库中表的记录进行更新。关键字：insert，delete，update等
数据控制语言：简称DCL(Data Control Language)，用来定义数据库的访问权限和安全级别，及创建用户。
数据查询语言：简称DQL(Data Query Language)，用来查询数据库中表的记录。关键字：select，from，where等
### SQL通用语法
SQL语句可以单行或多行书写，以分号结尾
可使用空格和缩进来增强语句的可读性
MySQL数据库的SQL语句不区分大小写，关键字建议使用大写,例如：SELECT * FROM user。
同样可以使用/**/的方式完成注释
MySQL常使用数据类型
## 3.2 DDL之数据库操作：database
### 创建数据库
```mysql
/*我们1*/
#都是
-- mysql的注释
 
-- 格式
create database 数据库名;
create database 数据库名 character set 字符集;
 
#例子
#创建数据库 数据库中数据的编码采用的是安装数据库时指定的默认编码
CREATE DATABASE webdb_1;
#创建数据库 并指定数据库中数据的编码
CREATE DATABASE webdb_2 CHARACTER SET utf8;
```
### 查看数据库
```mysql
-- 查看数据库MySQL服务器中的所有的数据库:
show databases;
 
#查看某个数据库的定义的信息:
show create database 数据库名称;
```
### 删除数据库
```mysql
-- 数据库不存在则报错
DROP DATABASE 数据库名称;
 
-- 判断数据库存在才删除（建议使用）:
DROP DATABASE IF EXISTS 数据库名称;
```
### 使用数据库
```mysql
-- 查看正在使用的数据库:
select database();
 
-- 切换数据库：
use 数据库名;
```
## 3.3 DDL之表操作：table
### 创建表
格式：
```mysql
create table 表名(
字段名 类型(长度) [约束1] [约束2] [约束3],
字段名 类型(长度) [约束1] [约束2] [约束3],
字段名 类型(长度) [约束1] [约束2] [约束3]
...
);
 
/*
类型：
varchar(n) 字符串
int  整形
double  浮点
date  时间
timestamp  时间戳
 
约束：(后面讲)
primary key 主键，被主键修饰字段中的数据，不能重复、不能为null。
*/
-- 例子 创建分类表
CREATE TABLE category (
cid INT primary key, #分类ID
cname VARCHAR(100) #分类名称
);
```
### 查看表
```mysql
-- 查看数据库中的所有表：
show tables;
-- 查看表结构：
desc 表名;
-- 例如：
desc category;
```
### 删除表
```mysql
-- 数据表不存在则报错
drop table 表名;
-- 判断数据表存在才删除（建议使用）:
drop table IF EXISTS 数据表名称;
```
### 修改表结构格式
```mysql
-- 添加列
alter table 表名 add 列名 类型(长度) [约束];
#例如：为分类表添加一个新的字段为 分类描述desc  varchar(20)
ALTER TABLE category ADD `desc` VARCHAR(20);
 
-- 修改列的类型长度及约束:
alter table 表名 modify 列名 类型(长度) 约束;
#例如：为分类表的描述字段进行修改，类型varchar(50) 添加约束 not null
ALTER TABLE category MODIFY `desc` VARCHAR(50) NOT NULL;

-- 修改列名
alter table 表名 change 旧列名 新列名 类型(长度) 约束;
#例如：为分类表的分类名称字段进行更换 更换为 description varchar(30)
ALTER TABLE category CHANGE `desc` description VARCHAR(30);

-- 删除列
alter table 表名 drop 列名;
#例如：删除分类表中description这列
ALTER TABLE category DROP description;

-- 修改表名
rename table 表名 to 新表名;
#例如：为分类表category 改名成 category2
RENAME TABLE category TO category2;

-- 修改表的字符集（了解）
alter table 表名 character set 字符集;
#例如：为分类表 category 的编码表进行修改，修改成 gbk
ALTER TABLE category CHARACTER SET gbk;
```
## 3.4 DML数据操作语言
### 插入表记录：insert
```mysql
-- 向表中插入某些字段
insert into 表 (字段1,字段2,字段3..) values (值1,值2,值3..);
-- 向表中插入所有字段,字段的顺序为创建表时的顺序
insert into 表 values (值1,值2,值3..);
 
-- 例子
INSERT INTO category(cid,cname) VALUES('c001','电器');
INSERT INTO category(cid,cname) VALUES('c002','服饰');
INSERT INTO category(cid,cname) VALUES('c003','化妆品');
INSERT INTO category(cid,cname) VALUES('c004','书籍');
INSERT INTO category(cid) VALUES('c005');
INSERT INTO category(cname,cid) VALUES('耗材','c006');

# 值与字段必须对应，个数相同，类型相同
# 值的数据大小必须在字段的长度范围内
# 除了数值类型外，其它的字段类型的值必须使用引号引起。（建议单引号）
# 如果要插入空值，可以不写字段，或者插入null。
```
### 更新表记录：update
```mysql
-- 更新所有记录的指定字段
update 表名 set 字段名=值,字段名=值,...;
-- 更新符合条件记录的指定字段
update 表名 set 字段名=值,字段名=值,... where 条件;
 
-- 例子
INSERT INTO category(cid,cname) VALUES('c001','电器');
INSERT INTO category(cid,cname) VALUES('c002','服饰');
INSERT INTO category(cid,cname) VALUES('c003','化妆品');
INSERT INTO category(cid,cname) VALUES('c004','书籍');
INSERT INTO category(cid) VALUES('c005');
INSERT INTO category(cname,cid) VALUES('耗材','c006');

# 列名的类型与修改的值要一致.
# 修改值得时候不能超过最大长度.
# 除了数值类型外，其它的字段类型的值必须使用引号引起
```
### 删除记录：delete
```mysql
-- 删除表中所有记录（表还在）
delete from 表名;
-- 删除符合条件的指定记录
delete from 表名 where 条件;
```
# 第4章 SQL约束
## 4.1 主键约束
PRIMARY KEY 约束唯一标识数据库表中的每条记录。

主键必须包含唯一的值。

主键列不能包含 NULL 值。

每个表都应该有一个主键，并且每个表只能有一个主键。

### 添加主键约束
```mysql
# 创建表时，在字段描述处，声明指定字段为主键
# 创建表时，在constraint约束区域，声明指定字段为主键
# 创建表之后，通过修改表结构，声明指定字段为主键
CREATE TABLE persons
(
id_p int PRIMARY KEY,
lastname varchar(255),
firstname varchar(255),
address varchar(255),
city varchar(255)，
-- CONSTRAINT PRIMARY KEY (id_p)
);
-- ALTER TABLE persons ADD PRIMARY KEY (id_p)
```
### 删除主键约束
```mysql
ALTER TABLE persons DROP PRIMARY KEY
```
## 4.2 自动增长列
我们通常希望在每次插入新记录时，数据库自动生成字段的值。我们可以在表中使用 auto_increment（自动增长列）关键字，自动增长列类型必须是整形，自动增长列必须为键(一般是主键)。

下列 SQL 语句把 "persons" 表中的 "p_id" 列定义为 auto_increment 主键
```mysql
CREATE TABLE persons
(
p_id int PRIMARY KEY AUTO_INCREMENT,
lastname varchar(255),
firstname varchar(255),
address varchar(255),
city varchar(255)
)
```
```
向persons添加数据时，可以不为p_id字段设置值，也可以设置成null，数据库将自动维护主键值：

INSERT INTO persons (firstname,lastname) VALUES ('Bill','Gates')
INSERT INTO persons (p_id,firstname,lastname) VALUES (NULL,'Bill','Gates')
默认AUTO_INCREMENT 的开始值是 1，如果希望修改起始值，请使用下列 SQL 语法：

ALTER TABLE persons AUTO_INCREMENT=100
问：针对auto_increment ，删除表中所有记录使用 delete from 表名 或使用 truncate table 表名，二者有什么区别？
delete 一条一条删除，不清空auto_increment记录数。
truncate 直接将表删除，重新建表，auto_increment将置为零，从新开始。
```
## 4.3 非空约束
```
NOT NULL 约束强制列不接受 NULL 值。
NOT NULL 约束强制字段始终包含值。这意味着，如果不向字段添加值，就无法插入新记录或者更新记录。
```
## 4.4 唯一约束
UNIQUE 约束唯一标识数据库表中的每条记录。 UNIQUE 和 PRIMARY KEY 约束均为列或列集合提供了唯一性的保证。 PRIMARY KEY 拥有自动定义的 UNIQUE 约束。

请注意，每个表可以有多个 UNIQUE 约束，但是每个表只能有一个 PRIMARY KEY 约束。

## 4.5 默认约束
对列添加默认约束,若不写入该列值,则使用默认值。
```mysql
CREATE TABLE persons11(
    -- 主键
    pid INT PRIMARY KEY AUTO_INCREMENT,
    -- 名
    lastname VARCHAR(20),
    -- 姓
    firstname VARCHAR(20),
    -- 城市
    city VARCHAR(10) DEFAULT '北京'
);
```
# 第五章 DQL语句
## 5.1 DQL准备工作
```mysql

#创建商品表：
create table product(
pid int primary key,
pname varchar(20),
price double,
category_id varchar(32)
);
INSERT INTO product(pid,pname,price,category_id) VALUES(1,'联想',5000,'c001');
INSERT INTO product(pid,pname,price,category_id) VALUES(2,'海尔',3000,'c001');
INSERT INTO product(pid,pname,price,category_id) VALUES(3,'雷神',5000,'c001');
INSERT INTO product(pid,pname,price,category_id) VALUES(4,'JACK JONES',800,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(5,'真维斯',200,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(6,'花花公子',440,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(7,'劲霸',2000,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(8,'香奈儿',800,'c003');
INSERT INTO product(pid,pname,price,category_id) VALUES(9,'相宜本草',200,'c003');
INSERT INTO product(pid,pname,price,category_id) VALUES(10,'面霸',5,'c003');
INSERT INTO product(pid,pname,price,category_id) VALUES(11,'好想你枣',56,'c004');
INSERT INTO product(pid,pname,price,category_id) VALUES(12,'香飘飘奶茶',1,'c005');
INSERT INTO product(pid,pname,price,category_id) VALUES(13,'果9',1,NULL);
```
语法：
```
select [distinct]
* | 列名,列名
from 表
where 条件
```
## 5.2 简单查询
练习：
```mysql
#查询所有的商品.
select * from product;
#查询商品名和商品价格.
select pname,price from product;
#别名查询.使用的关键字是as（as可以省略的）.表别名:
select * from product as p;
#别名查询.使用的关键字是as（as可以省略的）.列别名:
select pname as pn from product;
#去掉重复值.
select distinct price from product;
#查询结果是表达式（运算查询）：将所有商品的价格+10元进行显示.
select pname,price+10 from product;
```
## 1.3 条件查询
练习：
```mysql
#查询商品名称为“花花公子”的商品所有信息：
SELECT * FROM product WHERE pname = '花花公子'
#查询价格为800商品
SELECT * FROM product WHERE price = 800
#查询价格不是800的所有商品
SELECT * FROM product WHERE price != 800
SELECT * FROM product WHERE price <> 800
SELECT * FROM product WHERE NOT(price = 800)
#查询商品价格大于60元的所有商品信息
SELECT * FROM product WHERE price > 60;
#查询商品价格在200到1000之间所有商品
SELECT * FROM product WHERE price >= 200 AND price <=1000;
SELECT * FROM product WHERE price BETWEEN 200 AND 1000;
#查询商品价格是200或800的所有商品
SELECT * FROM product WHERE price = 200 OR price = 800;
SELECT * FROM product WHERE price IN (200,800);
#查询含有'霸'字的所有商品
SELECT * FROM product WHERE pname LIKE '%霸%';
#查询以'香'开头的所有商品
SELECT * FROM product WHERE pname LIKE '香%';
#查询第二个字为'想'的所有商品
SELECT * FROM product WHERE pname LIKE '_想%';
#商品没有分类的商品
SELECT * FROM product WHERE category_id IS NULL
#查询有分类的商品
SELECT * FROM product WHERE category_id IS NOT NULL
```
## 5.4 排序查询
通过order by语句，可以将查询出的结果进行排序。暂时放置在select语句的最后。

格式:
```
SELECT * FROM 表名 ORDER BY 排序字段 ASC|DESC;
#ASC 升序 (默认)
#DESC 降序
```
练习：
```mysql
#使用价格排序(降序)
SELECT * FROM product ORDER BY price DESC;
#在价格排序(降序)的基础上，以分类排序(降序)
SELECT * FROM product ORDER BY price DESC,category_id DESC;
#显示商品的价格(去重复)，并排序(降序)
SELECT DISTINCT price FROM product ORDER BY price DESC;
```
## 5.5 聚合查询
之前我们做的查询都是横向查询，它们都是根据条件一行一行的进行判断，而使用聚合函数查询是纵向查询，它是对一列的值进行计算，然后返回一个单一的值；另外聚合函数会忽略空值。

今天我们学习如下五个聚合函数：
```
count：统计指定列不为NULL的记录行数；
sum：计算指定列的数值和，如果指定列类型不是数值类型，那么计算结果为0；
max：计算指定列的最大值，如果指定列是字符串类型，那么使用字符串排序运算；
min：计算指定列的最小值，如果指定列是字符串类型，那么使用字符串排序运算；
avg：计算指定列的平均值，如果指定列类型不是数值类型，那么计算结果为0；
```
```mysql
#查询商品的总条数
SELECT COUNT(*) FROM product;
#查询价格大于200商品的总条数
SELECT COUNT(*) FROM product WHERE price > 200;
#查询分类为'c001'的所有商品的总和
SELECT SUM(price) FROM product WHERE category_id = 'c001';
#查询分类为'c002'所有商品的平均价格
SELECT AVG(price) FROM product WHERE category_id = 'c002';
#查询商品的最大价格和最小价格
SELECT MAX(price),MIN(price) FROM product;
```
## 5.6 分组查询
分组查询是指使用group by字句对查询信息进行分组。

格式：
```
SELECT 字段1,字段2… FROM 表名 GROUP BY分组字段 HAVING 分组条件;
分组操作中的having子语句，是用于在分组后对数据进行过滤的，作用类似于where条件。

having与where的区别:
having是在分组后对数据进行过滤.
where是在分组前对数据进行过滤
having后面可以使用分组函数(统计函数)
where后面不可以使用分组函数。
```
```mysql
#统计各个分类商品的个数
SELECT category_id ,COUNT(*) FROM product GROUP BY category_id ;
#统计各个分类商品的个数,且只显示个数大于1的信息
SELECT category_id ,COUNT(*) FROM product GROUP BY category_id HAVING COUNT(*) > 1;
```
# 第六章 多表操作
## 6.1 表与表之间的关系
```
一对多关系：
常见实例：客户和订单，分类和商品，部门和员工.
一对多建表原则：在从表(多方)创建一个字段，字段作为外键指向主表(一方)的主键.
多对多关系：
常见实例：学生和课程、用户和角色
多对多关系建表原则：需要创建第三张表,中间表中至少两个字段，这两个字段分别作为外键指向各自一方的主键.
一对一关系：(了解)
在实际的开发中应用不多.因为一对一可以创建成一张表.
```
## 6.2 外键约束
```
有两张表“分类表”和“商品表”，为了表明商品属于哪个分类，通常情况下，我们将在商品表上添加一列，用于存放分类cid的信息，此列称为：外键。
此时“分类表category”称为：主表，“cid”我们称为主键。“商品表products”称为：从表，category_id称为外键。我们通过主表的主键和从表的外键来描述主外键关系，呈现就是一对多关系。
```
外键特点：
```
从表外键的值是对主表主键的引用。
从表外键类型，必须与主表主键类型一致。
```
声明外键约束：
```mysql
#语法：
alter table 从表 add constraint [外键名称] foreign key (从表外键字段名) references 主表 (主表的主键);
#[外键名称]用于删除外键约束的，一般建议“_fk”结尾
alter table 从表 drop foreign key 外键名称;
```
使用外键目的：
保证数据完整性
## 6.2 一对多操作
category分类表，为一方，也就是主表，必须提供主键cid

products商品表，为多方，也就是从表，必须提供外键category_id
```mysql
#创建分类表
create table category(
cid varchar(32) PRIMARY KEY ,
cname varchar(100) -- 分类名称
);
# 商品表
CREATE TABLE `products` (
`pid` varchar(32) PRIMARY KEY ,
`name` VARCHAR(40) ,
`price` DOUBLE,
category_id varchar(32)
);
#添加约束
alter table products add constraint product_fk foreign key (category_id) references category (cid);
 
#1 向分类表中添加数据
INSERT INTO category (cid ,cname) VALUES('c001','服装');
#2 向商品表添加普通数据,没有外键数据，默认为null
INSERT INTO products (pid,pname) VALUES('p001','商品名称');
#3 向商品表添加普通数据，含有外键信息(category表中存在这条数据)
INSERT INTO products (pid ,pname ,category_id) VALUES('p002','商品名称2','c001');
#4 向商品表添加普通数据，含有外键信息(category表中不存在这条数据) -- 失败,异常
INSERT INTO products (pid ,pname ,category_id) VALUES('p003','商品名称2','c999');
#5 删除指定分类(分类被商品使用) -- 执行异常
DELETE FROM category WHERE cid = 'c001';
```
## 6.3 多对多
```
商品和订单多对多关系，将拆分成两个一对多。
products商品表，为其中一个一对多的主表，需要提供主键pid
orders 订单表，为另一个一对多的主表，需要提供主键oid
orderitem中间表，为另外添加的第三张表，需要提供两个外键oid和pid
```
```mysql
# 商品表
CREATE TABLE `products` (
`pid` VARCHAR(32) PRIMARY KEY ,
`name` VARCHAR(40) ,
`price` DOUBLE,
category_id VARCHAR(32)
);
#订单表
CREATE TABLE `orders`(
`oid` VARCHAR(32) PRIMARY KEY ,
`totalprice` DOUBLE #总计
);
#订单表和订单项表的主外键关系
CREATE TABLE orderitem(
oid VARCHAR(50),-- 订单id
pid VARCHAR(50)-- 商品id
);
 
ALTER TABLE `orderitem` ADD CONSTRAINT orderitem_orders_fk FOREIGN KEY (oid) REFERENCES orders(oid);
 
ALTER TABLE `orderitem` ADD CONSTRAINT orderitem_product_fk FOREIGN KEY (pid) REFERENCES products(pid);
 
ALTER TABLE `orderitem` ADD PRIMARY KEY (oid,pid);
 
#1 向商品表中添加数据
INSERT INTO products (pid,NAME) VALUES('p003','商品名称');
#2 向订单表中添加数据
INSERT INTO orders (oid ,totalprice) VALUES('x001','998');
INSERT INTO orders (oid ,totalprice) VALUES('x002','100');
#3向中间表添加数据(数据存在)
INSERT INTO orderitem(pid,oid) VALUES('p003','x001');
#4向中间表添加数据(数据不存在，添加失败)
INSERT INTO orderitem(pid,oid) VALUES('p001','x002');
INSERT INTO orderitem(pid,oid) VALUES('p002','x002');
#5删除商品表的数据(外键存在，添加失败)
DELETE FROM products WHERE pid = 'p003';
```
# 第七章 多表查询
## 7.1 初始化表结构
```mysql
# 分类表
CREATE TABLE category (
cid VARCHAR(32) PRIMARY KEY ,
cname VARCHAR(50)
);
#商品表
CREATE TABLE products(
pid VARCHAR(32) PRIMARY KEY ,
pname VARCHAR(50),
price INT,
flag VARCHAR(2), #是否上架标记为：1表示上架、0表示下架
category_id VARCHAR(32),
CONSTRAINT products_fk FOREIGN KEY (category_id) REFERENCES category (cid)
);
 
#初始化数据
#分类
INSERT INTO category(cid,cname) VALUES('c001','家电');
INSERT INTO category(cid,cname) VALUES('c002','服饰');
INSERT INTO category(cid,cname) VALUES('c003','化妆品');
#商品
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p001','联想',5000,'1','c001');
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p002','海尔',3000,'1','c001');
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p003','雷神',5000,'1','c001');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p004','JACKJONES',800,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p005','真维斯',200,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p006','花花公子',440,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p007','劲霸',2000,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p008','香奈儿',800,'1','c003');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p009','相宜本草',200,'1','c003');
```
## 7.2 多表查询
1. 交叉连接查询(基本不会使用-得到的是两个表的乘积) [了解]

语法： select * from A,B;
2. 内连接查询(使用的关键字 inner join -- inner可以省略)
```
隐式内连接： select * from A,B where 条件;
显示内连接： select * from A inner join B on 条件;
```
```mysql
#查询哪些分类的商品已经上架
#隐式内连接
SELECT DISTINCT c.cname FROM category c , products p WHERE c.cid = p.category_id AND p.flag = '1';
#内连接
SELECT DISTINCT c.cname FROM category c INNER JOIN products p ON c.cid = p.category_id WHERE p.flag = '1';
```
3. 外连接查询(使用的关键字 outer join -- outer可以省略)
```
左外连接：left outer join
select * from A left outer join B on 条件;
右外连接：right outer join
select * from A right outer join B on 条件;

#2.查询所有分类商品的个数
#左外连接
INSERT INTO category(cid,cname) VALUES('c004','奢侈品');
SELECT cname,COUNT(category_id) FROM category c LEFT OUTER JOIN products p ON c.cid = p.category_id GROUP BY cname;
```
## 7.3 子查询
```mysql
# 一条select语句结果作为另一条select语法一部分（查询条件，查询结果，表等）。
#子查询, 查询“化妆品”分类上架商品详情
#隐式内连接
SELECT p.* FROM products p , category c WHERE p.category_id=c.cid AND c.cname = '化妆品';
#子查询
##作为查询条件
SELECT * FROM products p WHERE p.category_id =(SELECT c.cid FROM category c WHERE c.cname='化妆品' );
#查询“化妆品”和“家电”两个分类上架商品详情
SELECT * FROM products p WHERE p.category_id in (SELECT c.cid FROM category c WHERE c.cname='化妆品' or c.name='家电');
```
作业
```mysql
# 表结构和初始化数据
drop database if exists emps;
create database emps;
use emps;
create table emp(
EMPNO INT, -- 员工号
ENAME VARCHAR(10), -- 员工姓名
JOB VARCHAR(9), -- 工作岗位
MGR int, -- 经理的员工号，外键
HIREDATE date, -- 入职时间
SAL double, -- 底薪
COMM double, -- 提成
DEPTNO int, -- 部门编号
primary key(EMPNO)
) ;
CREATE TABLE dept (
DEPTNO int, -- 部门编号
DNAME varchar(13), -- 部门名称
LOC VARCHAR(13), -- 部门地点
primary key(DEPTNO)
) ;
-- 插入数据
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7369,'SMITH','CLERK',7902,'2003-12-17',800,null,20);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7499,'ALLEN','SALESMAN',7698,'2007-9-3',1600,300,30);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7521,'WARD','SALESMAN',7698,'2005-3-8',1250,500,30);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7566,'JONES','MANAGER',7839,'2007-7-7',2975,null,20);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7654,'MARTIN','SALESMAN',7698,'2005-5-6',1250,1400,30);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7698,'BLAKE','MANAGER',7839,'2005-5-6',2850,null,30);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7782,'CLARK','MANAGER',7839,'2010-3-1',2450,null,10);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7788,'SCOTT','ANALYST',7566,'2005-5-6',3000,null,20);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7839,'KING','PRESIDENT',null,'2001-5-6',5000,null,10);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7844,'TURNER','SALESMAN',7698,'2008-8-8',1500,0,30);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7876,'ADAMS','CLERK',7788,'2005-5-6',1100,null,20);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7900,'JAMES','CLERK',7698,'2005-5-6',950,null,30);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7902,'FORD','ANALYST',7566,'2005-5-6',3000,null,20);
Insert into EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (7934,'MILLER','CLERK',7782,'2005-5-6',1300,null,10);
Insert into DEPT (DEPTNO,DNAME,LOC) values (10,'ACCOUNTING','NEW YORK');
Insert into DEPT (DEPTNO,DNAME,LOC) values (20,'RESEARCH','DALLAS');
Insert into DEPT (DEPTNO,DNAME,LOC) values (30,'SALES','CHICAGO');
Insert into DEPT (DEPTNO,DNAME,LOC) values (40,'OPERATIONS','BOSTON');
```


-- 1．列出至少有一个员工的所有部门。

-- 2．列出薪金比“SMITH”多或者相等的所有员工。

-- 3．列出所有员工的姓名及其直接上级的姓名。

-- 4．列出受雇日期早于其直接上级的所有员工。

-- 5．列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门

-- 6．列出所有“CLERK”（办事员）的姓名及其部门名称。

-- 7．列出最低薪金大于1500的各种工作。

-- 8．列出在部门“SALES”（销售部）工作的员工的姓名，假定不知道销售部的部门编号。

-- 9．列出薪金高于公司平均薪金的所有员工。

-- 10．列出与“SCOTT”从事相同工作的所有员工。

-- 11．列出薪金等于部门30中员工的薪金的所有员工的姓名和薪金。

-- 12．列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金。

-- 13．列出在每个部门工作的部门名称,员工数量、平均工资。

-- 14．列出所有员工的姓名、部门名称和工资(含奖金)。

-- 15．列出所有部门的详细信息和部门人数。

-- 16．列出各种工作的最低工资。

-- 17．列出各个MANAGER（经理）的最低薪金。

-- 18．列出所有员工的年工资,按年薪从低到高排序。