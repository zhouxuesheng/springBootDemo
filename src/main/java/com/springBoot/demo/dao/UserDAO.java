package com.springBoot.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBoot.demo.entity.User;


@Repository
public interface UserDAO extends JpaRepository<User,Long>{

	@Query(value = "select t from User t where pname=:pname")
	public User getUserByName(@Param("pname") String pname);
	
	
	// nativeQuery = true 表示使用原生态的SQL
	@Query(value = "insert into test_p values(?1,?2)",nativeQuery = true)
	@Modifying
	public void insertUser(String pname,String psex);

	
	@Query(value = "delete from test_p where pname = ?1" ,nativeQuery = true)
	public void deleteUser(String pname);
	 
}
