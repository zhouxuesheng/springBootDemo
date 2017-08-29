package org.springBootAopRedis.serivce;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springBootAopRedis.dao.PassPortInfoDAO;
import org.springBootAopRedis.entity.PassPortInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PassPortInfoService {


	private static final Logger LOGGER = LoggerFactory.getLogger(PassPortInfoService.class);
 
	@Autowired
	private PassPortInfoDAO passPortInfoDAO;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	
	
	public PassPortInfo checkUser(String passport,String userpsw){
		if(StringUtils.isEmpty(passport) && StringUtils.isEmpty(userpsw)){
			LOGGER.info("PassPortInfoService.checkUser()  isEmpty  用户或密码缺失...");
			return null;
		}else{
			
			String user_key = "user_" + passport;
			
			ValueOperations<String, PassPortInfo>  operations = redisTemplate.opsForValue();
			
			//判断缓存是否存在
			Boolean hasKey =  redisTemplate.hasKey(user_key);
			LOGGER.info("PassPortInfoService.checkUser()  查询缓存中的key" + user_key +" 是否存在 " + hasKey);
			//如果存在
			if(hasKey){
				PassPortInfo passPortInfo = operations.get(user_key);
				LOGGER.info("PassPortInfoService.checkUser() 缓存存在");
				return passPortInfo;
			}else{
				PassPortInfo portInfo =  new PassPortInfo();
				portInfo.setUserpsw(userpsw);
				portInfo.setPassport(passport);
				PassPortInfo passPortInfo = passPortInfoDAO.checkUser(portInfo);
				if(passPortInfo != null && passPortInfo.getGid() != null){
					//添加缓存信息 10分钟
					operations.set(user_key, passPortInfo,10,TimeUnit.MINUTES);	
					LOGGER.info("PassPortInfoService.checkUser() 缓存不存在，添加新的缓存");
					return passPortInfo;
				}else{
					return null;
				}
			}	
		}
	}
	
	public List<PassPortInfo> getUserList(String passport,String nickName){
		PassPortInfo portInfo =  new PassPortInfo();
		portInfo.setPassport(passport);
		portInfo.setNickName(nickName);
		return passPortInfoDAO.getUserList(portInfo);
	}
	
	public boolean updateUser(String userpsw,String nickName){
		if(StringUtils.isEmpty(userpsw) && StringUtils.isEmpty(nickName)){
			return false;
		}else{
			PassPortInfo portInfo =  new PassPortInfo();
			portInfo.setUserpsw(userpsw);
			portInfo.setNickName(nickName);
			passPortInfoDAO.updateUser(portInfo);
			return true;
		}
	}
	
	
	public boolean insertUser(String passport,String userpsw,String nickName){
		if(StringUtils.isEmpty(passport) && StringUtils.isEmpty(userpsw) && StringUtils.isEmpty(nickName)){
			return false;
		}else{
			PassPortInfo portInfo =  new PassPortInfo();
			portInfo.setUserpsw(userpsw);
			portInfo.setNickName(nickName);
			portInfo.setPassport(passport);
			portInfo.setGid(String.valueOf(new Random().nextInt(19)));
			passPortInfoDAO.insertUser(portInfo);
			return true;
		}
	}
	
	
	public boolean deleteUser(String gid){
		if(StringUtils.isEmpty(gid)){
			return false;
		}else{
			passPortInfoDAO.deleteUser(gid);
			return true;
		}
	}
	
	
	private static String getToKen(){
		
		String str= "abcdefghijklmnopqrstuvwxyz";

		StringBuffer token = new StringBuffer();
		for (int i = 0; i < 18; i++) {
			token.append(str.charAt((int)(Math.random()*26))) ;
		}
		return token.toString().toUpperCase();
//		System.out.println(chars.charAt((int)(Math.random() * 26)));
		
	}	
	
	
	public static void main(String[] args) {
		System.out.println(getToKen());
	}
	
	
}
