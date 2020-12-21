package model.entities;

import java.util.Date;

public class Exhibition {
	private String alias;
	private int user;
	private int media;
	private Date date;
	private int watchTime;
	
	public Exhibition(String alias, int user, int media, Date date, int watchTime) {
		super();
		this.alias = alias;
		this.user = user;
		this.media = media;
		this.date = date;
		this.watchTime = watchTime;
	}
	
	public String toString() {
		return "<" + alias + "," + Integer.toString(user) + "," + Integer.toString(media) + "," + date.toString() + "," + Integer.toString(watchTime) + ">";
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
	public int getWatchTime() {
		return watchTime;
	}
	public void setWatchTime(int watchTime) {
		this.watchTime = watchTime;
	}
}
