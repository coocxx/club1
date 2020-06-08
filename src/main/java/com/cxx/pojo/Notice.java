package com.cxx.pojo;

public class Notice {
	private int id;
	private int clubId;
	private String title;
	private String content;
	private String time;
	
	private Club club;

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int id, int clubId, String title, String content, String time,Club club) {
		super();
		this.id = id;
		this.clubId = clubId;
		this.title = title;
		this.content = content;
		this.time = time;
		this.club = club;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String text) {
		this.content = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", clubId=" + clubId + ", title=" + title + ", content=" + content + ", time=" + time
				+ ", club=" + club + "]";
	}

	
	
	
	
}
