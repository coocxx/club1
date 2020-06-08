package com.cxx.pojo;

/**
 * 社团类型
 * @author Cool
 *
 */
public class ClubType {
	private int id;
	private String name;
	public ClubType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClubType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "ClubType [id=" + id + ", name=" + name + "]";
	}
	
}
