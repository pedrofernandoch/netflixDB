package model.entities;

public class Profile {
	private String alias;
	private int user;
	private String type;
	
	public Profile(String alias, int user, String type) {
		super();
		this.alias = alias;
		this.user = user;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
