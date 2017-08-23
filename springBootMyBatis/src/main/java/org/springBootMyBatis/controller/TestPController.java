package org.springBootMyBatis.controller;

import java.util.List;

import org.springBootMyBatis.entity.TestP;
import org.springBootMyBatis.serivce.TestPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/testP")
public class TestPController {

	@Autowired
	private TestPService testPService;
	
	
	@RequestMapping(value = "/{name}", method= RequestMethod.GET)
	public String findByName(@PathVariable("name") String pname,Model model){
		
		TestP testP = testPService.findByName(pname);
		
		model.addAttribute("testP", testP);
		
		return "testP";
	}
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public String getAll(Model model){
		List<TestP> list =  testPService.getAll();
		
		model.addAttribute("list", list);
		
		return "list";
	}
	
}
