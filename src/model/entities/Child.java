package model.entities;

public class Child {
	private String alias;
	private int user;
	private int ageRange;
	private String adult;
	
	public Child(String alias, int user, int ageRange, String adult) {
		super();
		this.alias = alias;
		this.user = user;
		this.ageRange = ageRange;
		this.adult = adult;
	}
	
	public String toString() {
		return "<" + alias + "," + Integer.toString(user) + "," + Integer.toString(ageRange) + "," + adult + ">";
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
	public int getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}
	public String getAdult() {
		return adult;
	}
	public void setAdult(String adult) {
		this.adult = adult;
	}
}
