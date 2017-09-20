package com.jimeng.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jimeng.entity.User;
import com.jimeng.service.UserService;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@Transactional
public class SpringTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void myTest(){
		System.out.println(userService.findOne(1));
	}
	
	
	
	
	
	
}









