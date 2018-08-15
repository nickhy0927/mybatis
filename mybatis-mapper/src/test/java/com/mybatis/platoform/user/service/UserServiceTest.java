/**
 * 
 */
package com.mybatis.platoform.user.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:10:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void queryListByMapTest() {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		List<User> userList = userService.queryListByMap(paramMap);
		System.out.println(userList);
		System.out.println("测试成功...");
	}
}
