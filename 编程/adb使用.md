# adb使用
## kill \[options] <PACKAGE>
杀死与应用程序的包名称相关联的所有进程。该命令只会杀死安全的进程，不会影响用户体验。 
options参数如下：

    --user <USER_ID> ：使用指定的用户来启动activity，如果不输入，则使用当前用户执行
命令样例：

    adb shell am kill com.some.package
    
    王者荣耀包名：com.tencent.tmgp.sgame