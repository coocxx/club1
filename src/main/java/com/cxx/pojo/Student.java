package com.cxx.pojo;

import java.util.Date;

/**
 * 学生类
 * @author Cool
 *
 */
public class Student {

	//学号
	private int id;
	//学生姓名
	private String name;
	//学生密码
	private String password;
	private String sex;
	private String email;
	private String phone;
	private int status;
	
	private String time;
	private String hobby;
	private int classesId;
	private Classes classes;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, String password, String sex, String email, String phone, int status,
			String time, String hobby, int classesId, Classes classes) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.time = time;
		this.hobby = hobby;
		this.classesId = classesId;
		this.classes = classes;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public int getClassesId() {
		return classesId;
	}
	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", email=" + email
				+ ", phone=" + phone + ", status=" + status + ", time=" + time + ", hobby=" + hobby + ", classesId="
				+ classesId + ", classes=" + classes + "]";
	}
	
}
