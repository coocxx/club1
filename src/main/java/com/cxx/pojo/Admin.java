package com.cxx.pojo;


public class Admin {
	private int id;
	private String name;
	private String password;
	private int sex ;
	private String phone;
	private String email;
	private String time;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String name, String password, int sex, String phone, String email, String time) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", phone=" + phone
				+ ", email=" + email + ", time=" + time + "]";
	}

}
