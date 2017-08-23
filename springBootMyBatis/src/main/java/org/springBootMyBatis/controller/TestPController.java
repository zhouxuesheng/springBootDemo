package org.springBootMyBatis.controller;

import java.util.List;

import org.springBootMyBatis.entity.TestP;
import org.springBootMyBatis.serivce.TestPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testP")
public class TestPController {

	@Autowired
	private TestPService testPService;
	
	
	@RequestMapping(value = "/{name}", method= RequestMethod.GET)
	public TestP findByName(@PathVariable("name") String pname){
		return testPService.findByName(pname);
	}
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public List<TestP> getAll(){
		return testPService.getAll();
	}
	
}
