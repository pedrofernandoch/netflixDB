package model.entities;

public class GenderPreference {
	private String alias;
	private int user;
	private String gender;
	private float grade;
	
	public GenderPreference(String alias, int user, String gender, float grade) {
		super();
		this.alias = alias;
		this.user = user;
		this.gender = gender;
		this.grade = grade;
	}
	
	public String toString() {
		return "<" + alias + "," + Integer.toString(user) + "," + gender + "," + Float.toString(grade) + ">";
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
}
