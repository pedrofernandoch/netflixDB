package model.entities;

public class PaymentMethod {
	private int user;
	private String type;
	
	public PaymentMethod(int user, String type) {
		super();
		this.user = user;
		this.type = type;
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
