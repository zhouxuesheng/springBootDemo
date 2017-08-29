package org.springBootAopRedis.entity;

import java.io.Serializable;

public class PassPortInfo implements Serializable{

	private static final long serialVersionUID = 356756929532800071L;

	/**
	 * 主键id
	 */
 	 private String gid;
 	 
 	/**
  	 * 通行证帐号
  	 */
  	 private String passport;
  	/**
  	 * 通行证密码
  	 */
  	 private String userpsw;
  	 
 	/**
  	 * 用户名称（昵称）
  	 */
  	 private String nickName;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getUserpsw() {
		return userpsw;
	}

	public void setUserpsw(String userpsw) {
		this.userpsw = userpsw;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public PassPortInfo() {
		super();
	}

	public PassPortInfo(String gid, String passport, String userpsw, String nickName) {
		super();
		this.gid = gid;
		this.passport = passport;
		this.userpsw = userpsw;
		this.nickName = nickName;
	}

	public PassPortInfo(String passport, String userpsw) {
		super();
		this.passport = passport;
		this.userpsw = userpsw;
	}
  	 
	
}
