package org.springBootAopRedis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PassPortInfoController {

	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(Model model){
		
		return "login";
	}
}
