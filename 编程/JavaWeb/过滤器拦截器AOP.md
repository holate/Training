## 简介
###过滤器

过滤器可以拦截到方法的请求和响应（ServletRequest request, SetvletResponse response），并对请求响应做出响应的过滤操作，比如设置字符编码、鉴权操作。
###拦截器

拦截器可以在方法之前（preHandle）和方法执行之后（afterCompletion）进行操作，回调操作（postHandle），可以获取执行的方法的名称，请求（HttpServletRequest）。
+ preHandle
调用时间：Controller方法处理之前

执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行

若返回false，则中断执行，**注意：不会进入afterCompletion**
+ postHandle

调用前提：preHandle返回true

调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作

执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。

备注：postHandle虽然post打头，但post、get方法都能处理
+ afterCompletion

调用前提：preHandle返回true

调用时间：DispatcherServlet进行视图的渲染之后

多用于清理资源
### AOP切片

AOP操作可以对操作进行横向的拦截，最大的优势在于可以获取执行方法的参数，对方法进行统一的处理，常见使用日志，事务，请求参数安全验证等。
+ 顺序
## Filter与Interceptor联系与区别
1. 拦截器是基于java的反射机制，使用代理模式，而过滤器是基于函数回调。
2. 拦截器不依赖servlet容器，过滤器依赖于servlet容器。
3. 拦截器只能对action起作用，而过滤器可以对几乎所有的请求起作用（可以保护资源）。
4. 拦截器可以访问action上下文，堆栈里面的对象，而过滤器不可以。
## 执行顺序
请求->>过滤器->>拦截器-->AOP->>拦截器->>过滤器->>响应
## 资料
[简述SpringAop以及拦截器和过滤器](https://www.cnblogs.com/dugqing/p/8906013.html)
