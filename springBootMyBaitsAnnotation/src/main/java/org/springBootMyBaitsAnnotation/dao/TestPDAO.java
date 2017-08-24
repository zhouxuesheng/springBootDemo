package org.springBootMyBaitsAnnotation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springBootMyBaitsAnnotation.entity.TestP;

@Mapper  // 标志为 Mybatis 的 Mapper
public interface TestPDAO {

	
	@Select("select * from test_p where pName=#{pname}")
	@Results({
		@Result(property = "pName" ,column = "pname"),
		@Result(property = "pSex" , column = "psex")
	})
	TestP findByName(@Param("pname")  String pname);
	
	@Select("select * from test_p")
	@Results({
		@Result(property = "pName" ,column = "pname"),
		@Result(property = "pSex" , column = "psex")
	})
	List<TestP> getAll();
	
	@Insert("insert into test_p (pname,psex) values(#{pName},#{pSex})")
	void insertTestP(TestP testP);
	
}
