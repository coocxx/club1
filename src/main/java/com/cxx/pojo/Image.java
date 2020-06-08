package com.cxx.pojo;

public class Image {
	private int id;
	private String name;
	private String url;
	private int clubId;
	private Club club;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", url=" + url + ", clubId=" + clubId + ", club=" + club + "]";
	}
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image(int id, String name, String url, int clubId, Club club) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.clubId = clubId;
		this.club = club;
	}
	
}
