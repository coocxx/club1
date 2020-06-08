package com.cxx.pojo;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class EventMember {
	private int id;
	private int studentId;
	private int eventId;
	private int status;
	
	private Student student;
	private Event event;
	public EventMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventMember(int id, int studentId, int eventId, int status, Student student, Event event) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.eventId = eventId;
		this.status = status;
		this.student = student;
		this.event = event;
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
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
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
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	@Override
	public String toString() {
		return "EventMember [id=" + id + ", studentId=" + studentId + ", eventId=" + eventId + ", status=" + status
				+ ", student=" + student + ", event=" + event + "]";
	}
	
	
	
}
