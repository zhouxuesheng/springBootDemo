package org.springBootHibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springBootHibernate.dao.UserDAO;
import org.springBootHibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public User getUserByName(String userName) {

		User user = null;
		try {
			user = userDAO.getUserByName(userName);
		} catch (Exception e) {
		}

		return user;
	}
	
	@Transactional()
	public void insertUser(String pname,String psex){
		if(StringUtils.isEmpty(pname) || StringUtils.isEmpty(psex)){
		}else{
			userDAO.insertUser(pname, psex);;
		}
	}
	
	
	public List<User> getUserList(){
		List<User> list = userDAO.findAll();
		return list;
	}
}
