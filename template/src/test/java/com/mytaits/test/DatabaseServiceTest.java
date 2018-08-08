package com.mytaits.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class DatabaseServiceTest {

	@Test
	public void configTest() {
		System.out.println("测试代码...");
		PathMatcher matcher = new AntPathMatcher();  
        // 完全路径url方式路径匹配  
//         String requestPath="/user/list.htm?username=aaa&departmentid=2&pageNumber=1&pageSize=20";//请求路径  
//         String patternPath="/user/list.htm**";//路径匹配模式  
   
        // 不完整路径uri方式路径匹配  
        // String requestPath="/app/pub/login.do";//请求路径  
        // String patternPath="/**/login.do";//路径匹配模式  
        // 模糊路径方式匹配  
         String requestPath="/app/pub/login.do";//请求路径  
         String patternPath="/app/*/login.do";//路径匹配模式  
        // 包含模糊单字符路径匹配  
        //String requestPath = "/app/pub/login.do";// 请求路径  
        //String patternPath = "/**/lo?in.do";// 路径匹配模式  
        boolean result = matcher.match(patternPath, requestPath);
        System.out.println(result);
	}
}
