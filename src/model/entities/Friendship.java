package model.entities;

import java.util.Date;

public class Friendship {
	private String profile1;
	private int user1;
	private String profile2;
	private int user2;
	private Date acceptDate;
	private Date requestDate;
	
	public Friendship(String profile1, int user1, String profile2, int user2, Date acceptDate, Date requestDate) {
		super();
		this.profile1 = profile1;
		this.user1 = user1;
		this.profile2 = profile2;
		this.user2 = user2;
		this.acceptDate = acceptDate;
		this.requestDate = requestDate;
	}
	
	public String toString() {
		return "<" + profile1 + "," + Integer.toString(user1) + "," + profile2 + "," + Integer.toString(user2) + "," + acceptDate.toString() + "," + requestDate.toString() + ">";
	}
	
	public String getProfile1() {
		return profile1;
	}
	public void setProfile1(String profile1) {
		this.profile1 = profile1;
	}
	public int getUser1() {
		return user1;
	}
	public void setUser1(int user1) {
		this.user1 = user1;
	}
	public String getProfile2() {
		return profile2;
	}
	public void setProfile2(String profile2) {
		this.profile2 = profile2;
	}
	public int getUser2() {
		return user2;
	}
	public void setUser2(int user2) {
		this.user2 = user2;
	}
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

}
