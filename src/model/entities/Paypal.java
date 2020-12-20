package model.entities;

public class Paypal {
	private int user;
	private String email;
	private String password;
	
	public Paypal(int user, String email, String password) {
		super();
		this.user = user;
		this.email = email;
		this.password = password;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
