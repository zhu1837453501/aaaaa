package com.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

@Controller
public class TestController {
	/* 1.测试springmvc的运行环境（包含spring） */
	@RequestMapping("/testSpringmvc")
	public String testSpringmvc() {
		return "test";
	}

	/*
	 * @Autowired 可以通过类型匹配到spring容器中找到DoorMapper接口的实现类实例， 将实例赋值给dao这个变量
	 */
	@Autowired // 自动装配
	DoorMapper dao;

	/* 2.测试SSM的运行环境 **/
	@RequestMapping("/testssm")
	public String testssm() {
		// 查询所有门店信息(DoorMapper.findAll)
		List<Door> list = dao.findAll();
		for (Door door : list) {
			System.out.println(door);
		}
		return "test";
	}
}
