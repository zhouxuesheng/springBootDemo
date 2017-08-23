package org.springBootValidation.serivce;

import java.util.List;

import org.springBootValidation.dao.TestPDAO;
import org.springBootValidation.entity.TestP;
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
