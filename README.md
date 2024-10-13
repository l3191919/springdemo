# springdemo
## sentinel
com.lyz.config.sentinelconfig.SentinelAspectConfiguration 
sentinel 配置类
并且 new SentinelResourceAspect()
com.lyz.properties.FlowControlProperties
定义一个配置属性类来映射 application.yml 中的 flow-control.rules
实现代码
com.lyz.controller.PaymentController1.selectUserItemById2
com.lyz.controller.PaymentController1.selectUserItemById1

### jenkins测试
application.yml 的 xxl配置 
 http://127.0.0.1:8080/xxl-job-admin 需要调 8080端口一般需要修改
 appname: xxl-job-executor-sample 执行器AppName 可以在xxl-job可视化页面上看到
 
 xxl-job-admin 调度中心
 /xxl-job/xxl-job-admin/src/main/resources/application.properties
 修改datasource 相关的配置
 
 ### 注解验证
 com.lyz.annotation.NotNullNumber 自己写的自定义注解
  @NotNull(message = "不能为空") //javax.validation.constraints 自带的
  @NotEmpty(message = "不能为空字符串")//javax.validation.constraints 自带的
  需要在Controller写@Valid
  com.lyz.controller.PaymentController.insertPayment
  
### 设计模式
责任链
com.lyz.alibaba.pattern.zerenlian.InvestChainPatternService
单例
com.lyz.alibaba.pattern.danli.Singleton1 双检锁/双重校验锁
com.lyz.alibaba.pattern.danli.Singleton2 饿汉式
com.lyz.alibaba.pattern.danli.Singleton3 枚举
