package com.iss.minipro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iss.module.platform.user.mapper.UserMapper;
import com.iss.module.platform.user.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.iss.module.**.mapper")
public class MiniProApplicationTests {

	@Autowired
	private UserMapper userMapper;
	public void contextLoads() {
		System.out.println(userMapper);
		User user = userMapper.login("zhangsan");
		System.out.println(user.getRealName());
	}
	
	@Test
	public void queryRoleId() {
		
	}
}
