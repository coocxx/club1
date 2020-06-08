package com.cxx.pojo;

/**
 * 申请社团信息
 * @author Cool
 *
 */
public class ClubApply {
	private int id;
	private int studentId;
	private int clubTypeId;
	private int adminId;
	private String name;
	private String reason;
	private int status;
	private String time;
	
	private Student student;
	private ClubType clubType;
	private Admin admin;
	public ClubApply(int id, int studentId, int clubTypeId, int adminId, String name, String reason, int status,
			String time, Student student, ClubType clubType, Admin admin) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.clubTypeId = clubTypeId;
		this.adminId = adminId;
		this.name = name;
		this.reason = reason;
		this.status = status;
		this.time = time;
		this.student = student;
		this.clubType = clubType;
		this.admin = admin;
	}
	public ClubApply() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getClubTypeId() {
		return clubTypeId;
	}
	public void setClubTypeId(int clubTypeId) {
		this.clubTypeId = clubTypeId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public ClubType getClubType() {
		return clubType;
	}
	public void setClubType(ClubType clubType) {
		this.clubType = clubType;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "ClubApply [id=" + id + ", studentId=" + studentId + ", clubTypeId=" + clubTypeId + ", adminId="
				+ adminId + ", name=" + name + ", reason=" + reason + ", status=" + status + ", time=" + time
				+ ", student=" + student + ", clubType=" + clubType + ", admin=" + admin + "]";
	}
	
	
}
