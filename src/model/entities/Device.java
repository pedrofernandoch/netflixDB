package model.entities;

public class Device {
	private int user;
	private String name;
	private String operationalSystem;
	
	public Device(int user, String name, String operationalSystem) {
		super();
		this.user = user;
		this.name = name;
		this.operationalSystem = operationalSystem;
	}
	
	public String toString() {
		return "<" + Integer.toString(user) + "," + name + "," + operationalSystem + ">";
	}
	
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperationalSystem() {
		return operationalSystem;
	}
	public void setOperationalSystem(String operationalSystem) {
		this.operationalSystem = operationalSystem;
	}
}
