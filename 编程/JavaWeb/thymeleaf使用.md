# 基础
+ 1.引入Thymeleaf
    + 通过\<html xmlns:th="http://www.thymeleaf.org">命名空间，将静态页面转换为动态视图。需要进行动态处理的元素需要使用"th:"作为前缀；
    + 通过“@{}”引用web静态资源，如js、css、image等文件；
+ 2.访问model中的数据
    + 通过${}访问：如这句<span class="span2" th:text="${person.userName}"></span>，通过“${}” 获取
    + 通过\[\[${}]]访问：如下面这句
```js
<button class="btn btn-info" th:onclick="getName([[${person.userName}]],[[${person.age}]],this);">
    获取用户信息
</button>
```
这种方式一般用来在javascript中访问model中的数据
+ 3.model中的数据迭代
使用th:each来循环迭代，如
```thymeleafiterateexpressions
<tr th:each="room:${data.map.get('rooms')}">
    <td th:text="${room.roomname}"></td>
    <td th:text="${room.description}"></td>
    <td th:text="${room.location}"></td>
    <td th:text="${room.star}"></td>
    <td th:text="${room.price}"></td>
</tr>
```
person作为迭代元素来使用，这样在下面的元素中就可以通过${person.*}来获取对象的属性了。

+ 4.数据判断
```thymeleaftemplatesexpressions
<div th:if="${not #lists.isEmpty(people)}">
    .........省略......
</div>
```
上面代码中，在div内部使用列表数据之前要先判断列表是否为空，就用了${not #list.isEmpty(people)}这样的句式。

Thymeleaf还支持>、<、>=、<=、==、!=等作为条件比较。

以上就是这个入门实例中用到的Thymeleaf中的相关知识，需要注意的是下面这两句：
```js
1、<button class="btn" th:onclick="'getName(\''+[[${person.userName}]]+'\');'">获取姓名</button>

2、<button class="btn btn-info" th:onclick="getName([[${person.userName}]],[[${person.age}]],this);">获取用户信息</button>
```
这两句都是在HTML中调用js函数时传递model数据的写法，但是第一种会报错！！！
# 注意
1. thymeleaf页面跳转必须经过controller