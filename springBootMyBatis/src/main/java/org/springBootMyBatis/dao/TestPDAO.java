package org.springBootMyBatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springBootMyBatis.entity.TestP;

public interface TestPDAO {

	
	TestP findByName(@Param("pname")  String pname);
	
	List<TestP> getAll();
}
