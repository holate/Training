#实训仓库
****
#待看
[多线程](https://blog.csdn.net/xlgen157387/article/details/77920497)


#收藏
[java一文秒懂专栏](https://www.twle.cn/c/yufei/javatm/javatm-basic-executorservice.html)

[简单教程](https://www.twle.cn)
#小tips
***
+ 生成范围内唯一随机数
```java
ThreadLocalRandom.current().ints(1,31).distinct().forEach(System.out::println);
new Random().ints(1, 31).distinct().forEach(System.out::println);
```