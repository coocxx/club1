package com.cxx.pojo;

public class PrModel {
	private int id;
	private String content;
	private int studentId;
	private int status;
	
	private Student student;

	public PrModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrModel(int id, String content, int studentId, int status, Student student) {
		super();
		this.id = id;
		this.content = content;
		this.studentId = studentId;
		this.status = status;
		this.student = student;
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "PrModel [id=" + id + ", content=" + content + ", studentId=" + studentId + ", status=" + status
				+ ", student=" + student + "]";
	}

	
}
