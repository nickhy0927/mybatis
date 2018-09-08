#mybatis-page

mybais-page 是自定义的一个mybatis分页插件，方便系统集成，用户只需要集成到自己的系统中就可以实现自动分页功能。目前只支持mysql和oracle，当然用户也可以自己扩展自己需要的数据库分页。

### [中央库地址](http://search.maven.org/#artifactdetails%7Ccom.github.monee1988%7Cmybatis-page%7C0.0.1-RELEASE%7Cjar)：http://search.maven.org/#artifactdetails%7Ccom.github.monee1988%7Cmybatis-page%7C0.0.1-RELEASE%7Cjar
### [mvnrepository地址](http://mvnrepository.com/artifact/com.github.monee1988/mybatis-page)：http://mvnrepository.com/artifact/com.github.monee1988/mybatis-page
  maven坐标 :tw-1f1f2:
```
<dependency>
    <groupId>com.github.monee1988</groupId>
    <artifactId>mybatis-page</artifactId>
    <version>0.0.1-RELEASE</version>
</dependency>
 ```	
用法：
1. 这里是列表文本SqlSessionFactoryBean配置
```
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- <property name="configuration" value="classpath:mybatis-config.xml"/ -->
        <!-- 自动扫描entity目录, 省掉Configuration.xml里的手工配置 -->
        <property name="mapperLocations" value="classpath:mappers/*/*.xml" />
        <property name="plugins">
	           <array>
	                <!-- 定义加入mybatis-page分页拦截器 -->
		            <bean class="com.github.monee1988.mybatis.MybatisInterceptor">
		                <!-- 当前分页类型选择Mysql -->
		                <property name="dialectClass" value="com.github.monee1988.mybatis.dialect.MySqlDialect"/>
		            </bean>
                </array>
        </property>
</bean>            
```

2. 这里是列表文本为方便开发还加入了Mapper.xml的自动刷新功能，可以大大缩减开发效率，不用修改sql语句后频繁的重启服务，用法：只需要在spring配置文件中加入以下代码(目前只适合xml文件在mappers文件夹下的情况)，
区别第一个版本：该版本需要传入数据源以完成多数据源程XML的加载
```
	<!-- 动态加载sqlSessionFactory 特定的XML -->
	<bean class="com.github.monee1988.mybatis.MybatisMapperDynamicLoader">
		<property name="sqlSessionFactory" ref="sqlSessionFactory"/>
		<property name="mapperLocations">
			<array>
				<value>classpath:mapper/test1/read/*.xml</value>
			</array>
		</property>
	</bean>
	<!-- 动态加载sqlSessionFactory2 特定的XML -->
	<bean class="com.github.monee1988.mybatis.MybatisMapperDynamicLoader">
		<property name="sqlSessionFactory" ref="sqlSessionFactory2"/>
		<property name="mapperLocations">
			<array>
				<value>classpath:mapper/test2/read/*.xml</value>
			</array>
		</property>
	</bean>
```

3. 分页用法  返回类型page<T>  
  
  例如：Controller事例代码 
```
@RequestMapping(value = {"page"} ,method = RequestMethod.GET)
public String findPageList(ModelMap modelMap,@RequestParam(defaultValue = "1",required = false) Integer pageNo,
			@RequestParam(defaultValue = "20",required = false)
			Integer pageSize){
		
		Page<Test> result = testService.findPage(new Test(), new Page<Test>(pageNo,pageSize));
		
		modelMap.put("message", result );
		return "showMessage";
	}
```
  Service事例代码
```
@Override
public Page<Test> findPage(Test test, Page<Test> page) {
		
		test.setPage(page);
		page.setList(testdao.findList(test));
		
		return page;
}
```
     XML文件事例(事例没有按标准写完整的带字段的SQL语句，开发中不建议此写法)
```
<select id="findList" resultType="com.hp.entity.Test">
        select * from test
</select>
```