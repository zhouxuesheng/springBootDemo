package org.springBootAopRedis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springBootAopRedis.entity.PassPortInfo;

public interface PassPortInfoDAO {

	
	PassPortInfo checkUser(PassPortInfo portInfo);
	
	List<PassPortInfo> getUserList(PassPortInfo portInfo);
	
	void updateUser(PassPortInfo portInfo);
	
	void deleteUser(String id);
	
	void insertUser(PassPortInfo portInfo);
	
}
