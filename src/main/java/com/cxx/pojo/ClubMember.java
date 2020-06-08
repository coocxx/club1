package com.cxx.pojo;

public class ClubMember {
	private int id;
	private int studentId;
	private int clubId;
	
	private String time;
	private int status;
	
	private Student student;
	private Club club;
	public ClubMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClubMember(int id, int studentId, int clubId, String date, int status, Student student, Club club) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.clubId = clubId;
		this.time = date;
		this.status = status;
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
	public void setTime(String date) {
		this.time = date;
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
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	@Override
	public String toString() {
		return "ClubMember [id=" + id + ", studentId=" + studentId + ", clubId=" + clubId + ", time=" + time
				+ ", status=" + status + ", student=" + student + ", club=" + club + "]";
	}
	
}
