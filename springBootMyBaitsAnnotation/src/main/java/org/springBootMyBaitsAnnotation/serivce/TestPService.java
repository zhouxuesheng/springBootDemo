package org.springBootMyBaitsAnnotation.serivce;

import java.util.List;

import org.springBootMyBaitsAnnotation.dao.TestPDAO;
import org.springBootMyBaitsAnnotation.entity.TestP;
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
	
	
	public void insertTestP(TestP testP){
		testPDAO.insertTestP(testP);
	}
	
}
