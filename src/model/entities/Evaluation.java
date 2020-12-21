package model.entities;

import java.util.Date;

public class Evaluation {
	private String alias;
	private int user;
	private int media;
	private Date date;
	private int grade;
	
	public Evaluation(String alias, int user, int media, Date date, int grade) {
		super();
		this.alias = alias;
		this.user = user;
		this.media = media;
		this.date = date;
		this.grade = grade;
	}
	
	public String toString() {
		return "To be implemented...";
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getMedia() {
		return media;
	}
	public void setMedia(int media) {
		this.media = media;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
