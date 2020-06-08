package com.cxx.pojo;

public class Event {
	private int id;
	private String name;
	private String startTime;
	private String endTime;
	private String introduce;
	private String place;
	private String appTime;
	private String subTime;
	private int status;
	
	private int studentId;
	private int clubId;
	
	private Student student;
	private Club club;
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int id, String name, String startTime, String endTime, String introduce, String place, String appTime,
			String subTime, int status, int studentId, int clubId, Student student, Club club) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.introduce = introduce;
		this.place = place;
		this.appTime = appTime;
		this.subTime = subTime;
		this.status = status;
		this.studentId = studentId;
		this.clubId = clubId;
		this.student = student;
		this.club = club;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public String getSubTime() {
		return subTime;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
		return "Event [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", introduce=" + introduce + ", place=" + place + ", appTime=" + appTime + ", subTime=" + subTime
				+ ", status=" + status + ", studentId=" + studentId + ", clubId=" + clubId + ", student=" + student
				+ ", club=" + club + "]";
	}
	
	
}
