package com.poscoict.jblog.vo;

public class UserVo {
	private String name;
	private String id;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", id=" + id + ", password=" + password + "]";
	}
	
	
}
