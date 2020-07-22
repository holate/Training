# adb使用
## kill \[options] \<PACKAGE>
杀死与应用程序的包名称相关联的所有进程。该命令只会杀死安全的进程，不会影响用户体验。 
options参数如下：
```
--user <USER_ID> ：使用指定的用户来启动activity，如果不输入，则使用当前用户执行
```
命令样例：
```
adb shell am kill com.some.package
    
王者荣耀包名：com.tencent.tmgp.sgame
```
## 远程 adb 连接

1. 确保手机开启了usb调试
2. 用usb线把手机和电脑连接起来
3. 执行命令：adb tcpip 5555
4. adb connect 手机ip地址:5555，默认端口，可省略

## 文件传输

+ 从手机端向电脑复制文件
输入: adb pull 手机存储路径  电脑路径

    `adb pull  /sdcard/xxx  /Users/xxxx/xxx.tx`
+ 从电脑端向手机复制文件
输入: adb pull 电脑路径  手机存储路径 

    `adb push  /Users/xxxx/xxx.txt   /sdcard/xxx`