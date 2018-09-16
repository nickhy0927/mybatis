package com.iss.minipro;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.iss.module.**.mapper")
public class UserServiceTest {

	@Autowired
	WebApplicationContext context;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	public void query() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
