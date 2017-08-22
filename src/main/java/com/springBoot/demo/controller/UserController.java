package com.springBoot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springBoot.demo.entity.User;
import com.springBoot.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index(){
		return "user/index";
	}
	
	
	
	@RequestMapping(value = "/show")
	@ResponseBody
	public String getUser(@RequestParam("name") String name){
		
		if(org.springframework.util.StringUtils.isEmpty(name)){
			return null;
		}else{
			User user =  userService.getUserByName(name);
			return user.getPname()   + "/" + user.getPsex();
		}
	}
	
	@RequestMapping(value = "/users/{name}")
	@ResponseBody
	public String getUserName(@PathVariable("name")String name){
		 return String.format("user %s", name);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String getUserList(){
		
		List<User> list = userService.getUserList();
		return net.sf.json.JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(@RequestParam("pname")String pname,@RequestParam("psex")String psex){
		userService.insertUser(pname, psex);
		return "true";
	}
}
