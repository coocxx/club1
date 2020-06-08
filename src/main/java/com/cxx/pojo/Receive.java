package com.cxx.pojo;

public class Receive {
	private int id;
	private String content;
	private int studentId;
	private int status;
	private String time;
	private int type;
	
	private Student student;

	public Receive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Receive(int id, String content, int studentId, int status, String time, int type, Student student) {
		super();
		this.id = id;
		this.content = content;
		this.studentId = studentId;
		this.status = status;
		this.time = time;
		this.type = type;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Recieve [id=" + id + ", content=" + content + ", studentId=" + studentId + ", status=" + status
				+ ", time=" + time + ", type=" + type + ", student=" + student + "]";
	}
	
	
}
