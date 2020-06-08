package com.cxx.pojo;

import java.util.List;

public class StuClub {
	
	private int studentId;
	// 某个学生加入的社团数
	private int count;
	
	private List<ClubMember> clubMembers;

	public StuClub() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StuClub(int studentId, int count, List<ClubMember> clubMembers) {
		super();
		this.studentId = studentId;
		this.count = count;
		this.clubMembers = clubMembers;
	}

	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ClubMember> getClubMembers() {
		return clubMembers;
	}

	public void setClubMembers(List<ClubMember> clubMembers) {
		this.clubMembers = clubMembers;
	}

	@Override
	public String toString() {
		return "StuClub [studentId=" + studentId + ", count=" + count + ", clubMembers="
				+ clubMembers + "]";
	}
	
}
