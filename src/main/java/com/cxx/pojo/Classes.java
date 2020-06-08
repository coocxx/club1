package com.cxx.pojo;

public class Classes {
	private int id;
	private String name;
	private int majorId;
	private Major major;
	public Classes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classes(int id, String name, int majorId, Major major) {
		super();
		this.id = id;
		this.name = name;
		this.majorId = majorId;
		this.major = major;
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
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name + ", majorId=" + majorId + ", major=" + major + "]";
	}
	
	
}
