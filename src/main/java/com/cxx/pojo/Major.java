package com.cxx.pojo;

public class Major {
	private int id;
	private String name;
	private int academyId;
	private Academy academy;
	public Major() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Major(int id, String name, int academyId, Academy academy) {
		super();
		this.id = id;
		this.name = name;
		this.academyId = academyId;
		this.academy = academy;
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
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	@Override
	public String toString() {
		return "Major [id=" + id + ", name=" + name + ", academyId=" + academyId + ", academy=" + academy + "]";
	}
	
}
