#实训仓库
****
#待看
[多线程](https://blog.csdn.net/xlgen157387/article/details/77920497)

[java一文秒懂专栏](https://www.twle.cn/c/yufei/javatm/javatm-basic-executorservice.html)

#收藏
[简单教程](https://www.twle.cn)
#小tips
+ 生成范围内唯一随机数
```java
ThreadLocalRandom.current().ints(1,31).distinct().forEach(System.out::println);
new Random().ints(1, 31).distinct().forEach(System.out::println);
```
#其他学习
+ git
    - 首次提交但是远程库有其他内容
        
        [git - 'set-upstream'有什么作用？](https://www.itranslater.com/qa/details/2117327610333103104)
        
        [解决办法以及原因](https://www.cnblogs.com/daemon369/p/3204646.html)
        
```
git查看远程分支
git branch -a
git查看本地分支
git branch
创建分支
git branch test
把分支推到远程分支 
git push origin test
切换分支
git checkout test
删除分支
git branch -d test
删除远程分支
git branch -r -d origin/branch-name
强制push的方法：
git push -u origin master -f 
```
 + windowns-cmd
```
新建文件: type nul>filename
查看文件列表: dir
向文件追加文本: echo .idea >>filename
查看文件内容: type filename
```