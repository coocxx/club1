package com.cxx.pojo;

public class News {

	private int id;
	private String title;
	private String content;
	private String people;
	private String time;
	private int count;
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content + ", people=" + people + ", time=" + time
				+ ", count=" + count + "]";
	}
	public News(int id, String title, String content, String people, String time, int count) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.people = people;
		this.time = time;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setContent(String content) {
		this.content = content;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
