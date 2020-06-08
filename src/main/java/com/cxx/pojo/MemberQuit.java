package com.cxx.pojo;

public class MemberQuit {
	private int id;
	private int studentId;
	private int clubId;
	private String time;
	
	private Student student;
	private Club club;
	public MemberQuit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberQuit(int id, int studentId, int clubId, String time, Student student, Club club) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.clubId = clubId;
		this.time = time;
		this.student = student;
		this.club = club;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	@Override
	public String toString() {
		return "MemberQuit [id=" + id + ", studentId=" + studentId + ", clubId=" + clubId + ", time=" + time
				+ ", student=" + student + ", club=" + club + "]";
	}
	
	
}
