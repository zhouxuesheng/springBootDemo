package org.springBootValidation.controller;

import java.util.List;

import org.springBootValidation.constant.ErrorInfoEnum;
import org.springBootValidation.entity.TestP;
import org.springBootValidation.error.GlobalErrorInfoException;
import org.springBootValidation.error.ResultBody;
import org.springBootValidation.serivce.TestPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/testP")
public class TestPController {

	@Autowired
	private TestPService testPService;
	
	
	@RequestMapping(value = "/get/{name}", method= RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/getRrror" , method = RequestMethod.GET)
	@ResponseBody
	public ResultBody getError(@RequestParam("name") String name) throws GlobalErrorInfoException{
		if(org.springframework.util.StringUtils.isEmpty(name)){
			throw new GlobalErrorInfoException(ErrorInfoEnum.PARAMS_NO_COMPLETE);
		}
		return new ResultBody(new TestP("zhangsan","1"));
	}
	
}
