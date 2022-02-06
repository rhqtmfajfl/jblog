package com.poscoict.jblog.vo;

public class CategoryVo {
	private int no;
	private String name;
	private String description;
	private String blog_user_id;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBlog_user_id() {
		return blog_user_id;
	}
	public void setBlog_user_id(String blog_user_id) {
		this.blog_user_id = blog_user_id;
	}
	
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", blog_user_id="
				+ blog_user_id + "]";
	}
	
	
}
