package model.entities;

import java.util.Date;

public class Recommendation {
	private String alias;
	private int user;
	private String friendProfile;
	private int friendUser;
	private int media;
	private Date date;
	private String comment;
	
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
	public String getFriendProfile() {
		return friendProfile;
	}
	public void setFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
	}
	public int getFriendUser() {
		return friendUser;
	}
	public void setFriendUser(int friendUser) {
		this.friendUser = friendUser;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
