# Git
# 部分命令
## git commit
    提交：git commit
## git branch
    查看本地分支：git branch
    查看远程分支：git branch -a
    
    创建分支：git branch <branch-name>
    
    删除分支：git branch -d <branch-name>
    删除远程分支：git branch -r -d origin/<branch-name>
## git checkout
    切换分支到指定节点：git checkout <branch-name> <sha-value>
    创建并切换到分支：git checkout -b <branch-name>
    
    切换到指定分支前一次提交：git checkout <branch-name>^
    切换到指定分支前num次提交：git checkout <branch-name>~<num>
    eg：
    git branch -f master HEAD~3
    上面的命令会将 master 分支强制指向 HEAD 的第 3 级父提交
    
## git push
    把分支推到远程分支  git push origin <branch-name>
    强制push的方法：git push -u origin master -f 
## git merge
    合并分支：git merge <branch-name>
## git rebase
    第二种合并分支的方法是 git rebase。
    Rebase 实际上就是取出一系列的提交记录，“复制”它们，然后在另外一个地方逐个的放下去。
    Rebase 的优势就是可以创造更线性的提交历史，这听上去有些难以理解。
    如果只允许使用 Rebase 的话，代码库的提交历史将会变得异常清晰。
## git log
查看日志

# 问题
首次提交但是远程库有其他内容?

[git - 'set-upstream'有什么作用？](https://www.itranslater.com/qa/details/2117327610333103104)

[解决办法以及原因](https://www.cnblogs.com/daemon369/p/3204646.html)
# 资料
+ [菜鸟教程Git](https://www.runoob.com/git/git-tutorial.html)
+ [Git命令学习](https://oschina.gitee.io/learn-git-branching)