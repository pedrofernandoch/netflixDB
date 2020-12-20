package model.entities;

public class CreditCard {
	private int user;
	private int cardNumber;
	
	public CreditCard(int user, int cardNumber) {
		super();
		this.user = user;
		this.cardNumber = cardNumber;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
}
