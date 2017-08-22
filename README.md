Spring Boot 项目建构说明

直接接触过Spring MVC 的简单项目，但是没用具体了解过，今天笔者就自己尝试的搭建一下Spring Boot的架构。
同时为了开发的方便，也安装了本地的Maven，但是在win10系统下。遇见几个大坑，安装后总是提示JAVA_HOME 指定错误问题
后面发现是JAVA_HOME环境变量 配置1.7、1.8的 2个JDK版本，而且最主要的是配置JAVA_HOME的环境变量时。最后不能带;否则Maven 还是出错。只是初略的找到了问题，没用更深层的了解。

1、通过Eclipse 新建一个Maven Project 项目

groupId = 包名
artifactId = 项目名

2、新建完成后，我们先打开pom.xml 文件， 
引入：
继承starter parent

 <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.2.5.RELEASE</version>
    <relativePath/>
  </parent>
  
  
  
SpringBoot包含一个Maven插件，它可以将项目打包成一个可执行jar。如果想使用它，你可以将该插件添加到<plugins>节
	<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
  
MySql 
	<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
	<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.39</version>
	</dependency>

Oracle 
	
	<!-- https://mvnrepository.com/artifact/com.oracle/ojdbc14 -->
	<dependency>
		<groupId>com.oracle</groupId>
		<artifactId>ojdbc14</artifactId>
		<version>10.2.0.4.0</version>
	</dependency>	
	
thymeleaf模板
	<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
		<version>1.4.0.RELEASE</version>
	</dependency>
Spring Data JPA	
	<!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
		<version>1.5.1.RELEASE</version>
	</dependency>	
	
	
3、完成pom.xml修改后
在src/main/resources下 新建application.properties
我们配置 服务的端口与编码格式
server.port=8080
server.tomcat.uri-encoding=utf-8


配置数据库连接
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@192.168.10.56:1521:sxlib
spring.datasource.username=tt_rd
spring.datasource.password=tt_rdoracle


Spring Date JPA配置
spring.jpa.database=ORACLE
spring.jpa.show-sql=true


视图层控制
spring.mvc.view.prefix=classpath:/templates/
spring.mvc.view.suffix=.html
spring.mvc.static-path-pattern=/static/**


4、服务的新建
创建 Entity，DAO，Service，Controller
Entity 要对类注解@Entity 同时需要注解主键@Id  否则出现异常： No identifier specified for entity

DAO 新建接口，注解@Repository  同时 继承JpaRepository ，在DAO层直接编写HQL语句进行操作

Service 新建实现类，注解@Service

Controller 新建实现类 注解@Controller 同时 标记页面的请求方式 @RequestMapping()， 对方法也可以@RequestMapping,通过RequestMapping来设置页面的请求方式，同时在方法中通过@RequestParam获取参数
同时也可以用 @PathVariable 方式获取请求地址中的参数，例如我们实际在请求地址有 
http://lolcahost:8080/user/11;http://lolcahost:8080/user/22; 分别查询用户11 与用户22的个人信息，通过PathVariable 配置@RequestMapping /user/{username} ，此时username就是11或22

在实际的业务场景中，我们可能是将Controller的内容返回到页面中，这是我们在方法上注解@ResponseBody，否则return的就是一个实际的页面路径地址，这种都是和SpringMVC都是一样的。


5、SpringBoot运行
在网上的其他案例中，我们要简单的运行1个SpringBoot 输出Hello Word ，是比较简单的。直接在方法上注解@SpringBootApplication，然后写Main方法 运行这个类本身，然后在页面请求地址中输入对于的方法路径就会输出Hello Word。网上也有很多这样的案例。但是这里只是加载的单个类，在实际的应用中，我们的项目是要在1个容器下运行的，例如Tomcat。在SpringBoot 中，默认的容器本身就的Tomcat。
我们通过配置的方式就可以完成SpringBoot的运行
新建一个configuration包，用于存放项目的配置类。新建一个JpaConfiguration.java类。Spring配置Java POJO类包路径以及DAO层接口路径，以自动扫描相关注解，是通过xml来配置的，但是这里是用过Java文件来配置的

@Order(Ordered.HIGHEST_PRECEDENCE)  配置类加载顺序最高级
@Configuration也是注解在类上面的，声明这是个配置作用的bean，替代Spring xml配置
@EnableTransactionManagement 事务控制
@EnableJpaRepositories 自动扫描DAO
@EntityScan 自动扫描entity
@Bean 相当于spring.xml 中的bean



这里也可以看出，SpringBoot 对xml的文件配置已经抛开了。通过对JAVA注解的方式来完成配置




	
	
	
