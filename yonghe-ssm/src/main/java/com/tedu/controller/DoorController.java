package com.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/**
 * 通用的页面跳转方法 当浏揽器访问的路径为：localhost/yonghe-ssm/index
 * {}中page的值就是index，再将page的值传递给方法上的 page变量（参数），最后在方法中return page 。
 * 这样就意味着，访问路径为/index，return的就是"index" 访问路径为/_left,return的值就是"_left"
 * pages/door_list.jsp
 */
@Controller
public class DoorController {
	@Autowired
	DoorMapper dao;

	/**
	 * 1.查询所有门店 跳转到门店列表(door_list.jsp)页面显示所有门店
	 */

	@RequestMapping("/doorList")
	public String doorList(Model model) {
		// 查询所有门店
		List<Door> list = dao.findAll();
		// 将门店集合存入Model中
		model.addAttribute("list", list);
		// 转向door_list.jsp页面取出所有门店进行显示
		return "door_list";// 自动跳转
	}

	// 新增门店信息
	@RequestMapping("/doorAdd")
	public String doorAdd(Door door) {
		// 1.调用service层的addDoor方法，新增门店信息
		dao.add(door);
		// 2.重定向到门店列表页面, 显示所有门店信息
		return "forward:/doorList";
	}

	/** 3.根据id删除门店信息 */
	@RequestMapping("/doorDelete")
	public String doorDelete(Integer id) {
		dao.deleteById(id);
		return "forward:/doorList";
	}

	/***/
	@RequestMapping("doorInfo")
	public String doorInfo(Integer id, Model model) {
		// 根据id查询门店信息，返回当前门店信息的door对象
		Door door = dao.findById(id);
		model.addAttribute("door", door);
		// 转向门店修改页面，进行数据的回显
		return "door_update";
	}

	/** 5.根据id修改门店信息 */
	@RequestMapping("/doorUpdate")
	public String doorUpdate(Door door) {
		dao.updateById(door);
		return "forward:/doorList";
	}

	@RequestMapping("/{page}")
	public String toPage(@PathVariable String page) {
		return page;
	}
}
