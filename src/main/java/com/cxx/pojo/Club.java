package com.cxx.pojo;

/**
 * 社团类型
 * @author Cool
 *
 */
public class Club {
	private int id;
	private int clubTypeId;
	private int studentId;
	
	private String name;
	private String place;
	private String introduce;
	private String notice;
	private String time;
	private int status;
	
	private ClubType clubType;
	private Student student;
	public Club(int id, int clubTypeId, int tudentId, String name, String place, String introduce, String notice,
			String time, int status, ClubType clubType, Student student) {
		super();
		this.id = id;
		this.clubTypeId = clubTypeId;
		this.studentId = tudentId;
		this.name = name;
		this.place = place;
		this.introduce = introduce;
		this.notice = notice;
		this.time = time;
		this.status = status;
		this.clubType = clubType;
		this.student = student;
	}
	public Club() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClubTypeId() {
		return clubTypeId;
	}
	public void setClubTypeId(int clubTypeId) {
		this.clubTypeId = clubTypeId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int tudentId) {
		this.studentId = tudentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ClubType getClubType() {
		return clubType;
	}
	public void setClubType(ClubType clubType) {
		this.clubType = clubType;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Club [id=" + id + ", clubTypeId=" + clubTypeId + ", studentId=" + studentId + ", name=" + name
				+ ", place=" + place + ", introduce=" + introduce + ", notice=" + notice + ", time=" + time
				+ ", status=" + status + ", clubType=" + clubType + ", student=" + student + "]";
	}
	
	
}
