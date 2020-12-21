package model.entities;

import java.util.Date;

public class Opinion {
	private String alias;
	private int user;
	private int media;
	private Date date;
	private String text;
	
	public Opinion(String alias, int user, int media, Date date, String text) {
		super();
		this.alias = alias;
		this.user = user;
		this.media = media;
		this.date = date;
		this.text = text;
	}
	
	public String toString() {
		return "<" + alias + "," + Integer.toString(user) + "," + Integer.toString(media) + "," + date.toString() + "," + text + ">";
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
