# snow
学习Github的的操作

**一. Spring boot 整合 Swagger2**
    
    Swagger是全球最大的开源API规范（OAS）API开发工具框架，支持从设计和文档到测试和部署的整
    个API生命周期的开发。

    1. 在 pom.xml 中添加依赖
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${io.springfox.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${io.springfox.version}</version>
        </dependency>
    2. 添加 Swagger2Config 类进行参数的配置
        com.msnowqueen.mqueen.config.Swagger2Config
    3. 对 Swagger2 进行测试

**二. Spring boot 整合 Logback**

    1. 在 Spring boot 的 jar 包中已经包含了 Logback 的 jar 包, 所以不需要再进行依赖.
    2. 在 logback-spring.xml 中对日志的打印进行配置, 根据日志的打印级别进行配置; 因为项目会
        分为开发模式和生产模式, 所以也要进行分类, 而两个模式的切换需要在 application.yml 中
        进行.
    3. logback-spring.xml 配置完成后, 就可以直接运行, 不需要再添加其他的配置信息.
    4. 对日志的打印进行测试

**三. Spring boot 整合 Mybatis**

    1. 异常错误信息之一
    Caused by: com.mysql.cj.exceptions.InvalidConnectionAttributeException: The server time zone value 
    'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone. You must configure either the 
    server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time 
    zone value if you want to utilize time zone support.
    
        原因: 是因为mysql的时区的错误造成的
        解决方法: 在配置文件配置数据源的url部分添加 + serverTimezone=GMT
    
    2. 异常错误信息之二
    Fri Sep 28 11:30:29 CST 2018 WARN: Establishing SSL connection without server's identity verification 
    is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must 
    be established by default if explicit option isn't set. For compliance with existing applications 
    not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly 
    disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate 
    verification.
    
        原因: 不建议在没有服务器身份验证的情况下建立SSL连接, 如果没有显示的设置, 会默认的进行SSL连接
        解决方法: 在配置文件配置数据源的url部分添加 + useSSL=false
        
    3. 异常错误信息之三
    Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is 
    `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of 
    the driver class is generally unnecessary.
    
        原因: 在mysql更新之后, `com.mysql.jdbc.Driver' 就不进行使用, 而是使用 `com.mysql.cj.jdbc.Driver'
        解决方法: 将配置数据源中的该数据改为: driver-class-name: com.mysql.cj.jdbc.Driver
     
    4. 异常错误信息之五
    testWhileIdle is true, validationQuery not set
    
        原因: 发现validationQuery没有自动注入, 查资料发现, springboot1.4 取消了spring.datasource.type参数. 
            需要手动声明 datasource bean.

**四. Spring boot 整合 Druid**

    1. 在pom中添加依赖
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>${com.alibaba.version}</version>
        </dependency>
    2. 在配置文件application.yml中添加配置信息
        druid:
                # 配置测试查询语句
              validationQuery: SELECT 1 FROM DUAL
                # 初始化大小，最小，最大
              initialSize: 10
              minIdle: 10
              maxActive: 200
                # 配置一个连接在池中最小生存的时间，单位是毫秒
              minEvictableIdleTimeMillis: 180000
              testOnBorrow: false
              testWhileIdle: true
              removeAbandoned: true
              removeAbandonedTimeout: 1800
              logAbandoned: true
                # 打开PSCache，并且指定每个连接上PSCache的大小
              poolPreparedStatements: false
              maxOpenPreparedStatements: 100
                # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
                # filters: stat,wall,log4j
                # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
              connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
    3. 添加DruidConfiguration类配置进行页面的监控

**五. Spring boot 整合 AOP**

    1. 在pom中添加依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
    2. 之后就可以定义一个切面类, 在里面定义需要进行打印日志的操作
        com.msnowqueen.mqueen.config.WebLogAspect

**六. Spring boot 整合 thymeleaf**

    1. 在pom中添加依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    2. 如果想对thymeleaf进行参数的自定义, 可以在application.yml中进行修改

**六. Spring boot 整合 统一异常返回**

    1. 使用@ControllerAdvice进行统一异常的接收管理
    2. 使用@ExceptionHandler定义接收到的异常, 对其进行管理, 在内部实现具体的流程
    3. 可以自定义配置异常的处理

**五. Spring boot 整合 generator 和通用 mapper**

**六. Spring boot 整合 邮件的发送**





