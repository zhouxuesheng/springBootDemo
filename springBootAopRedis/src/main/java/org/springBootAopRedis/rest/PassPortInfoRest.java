package org.springBootAopRedis.rest;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springBootAopRedis.constant.ErrorInfoEnum;
import org.springBootAopRedis.entity.PassPortInfo;
import org.springBootAopRedis.error.GlobalErrorInfoEnum;
import org.springBootAopRedis.error.ResultBody;
import org.springBootAopRedis.serivce.PassPortInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PassPortInfoRest {

	
	@Autowired
	private PassPortInfoService passPortInfoService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResultBody login(@Param("passport") String passport,@Param("userpsw") String userpsw){
		
		PassPortInfo user = passPortInfoService.checkUser(passport, userpsw);
		if(user == null){
			return new ResultBody(ErrorInfoEnum.RESULT_NOT_NULL);
		}else{
			//... 登陆成功后的操作
			return new ResultBody(user);
		}
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResultBody getUserList(@Param("passport") String passport,@Param("nickName") String nickName){
		
		List<PassPortInfo> list = passPortInfoService.getUserList(passport, nickName);
		return new ResultBody(list);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ResultBody addUser(@Param("passport") String passport,@Param("userpsw") String userpsw,@Param("nickName") String nickName){
		boolean flg = passPortInfoService.insertUser(passport, userpsw, nickName);
		if(flg){
			return new ResultBody(flg);
		}else{
			return new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ResultBody editUser(@Param("userpsw") String userpsw,@Param("nickName") String nickName){
		
		boolean flg = passPortInfoService.updateUser(userpsw, nickName);
		if(flg){
			return new ResultBody(flg);
		}else{
			return new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ResultBody delUser(@Param("gid") String gid){
		boolean flg = passPortInfoService.deleteUser(gid);
		if(flg){
			return new ResultBody(flg);
		}else{
			return new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
		}
	}
}
