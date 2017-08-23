package org.springBootMyBatis.serivce;

import java.util.List;

import org.springBootMyBatis.dao.TestPDAO;
import org.springBootMyBatis.entity.TestP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPService {

	@Autowired
	private TestPDAO testPDAO;
	
	
	public TestP findByName(String pname){
		
		return testPDAO.findByName(pname);
	}
	
	public List<TestP> getAll(){
		return testPDAO.getAll();
	}
	
}
