package org.springBootMyBaitsAnnotation.entity;

public class TestP {

	/** 姓名 */
	private String pName;
	
	/** 性别*/
	private String pSex;

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpSex() {
		return pSex;
	}

	public void setpSex(String pSex) {
		this.pSex = pSex;
	}

	public TestP(String pName, String pSex) {
		this.pName = pName;
		this.pSex = pSex;
	}

	public TestP() {
	}
	
	
}
