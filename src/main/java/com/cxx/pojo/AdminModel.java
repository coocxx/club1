package com.cxx.pojo;

public class AdminModel {
	private int id;
	private String content;
	private int adminId;
	private int type;
	private int status;
	
	private Admin admin;

	public AdminModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminModel(int id, String content, int adminId, int type, int status, Admin admin) {
		super();
		this.id = id;
		this.content = content;
		this.adminId = adminId;
		this.type = type;
		this.status = status;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "AdminModel [id=" + id + ", content=" + content + ", adminId=" + adminId + ", type=" + type + ", status="
				+ status + ", admin=" + admin + "]";
	}

	
	
}
