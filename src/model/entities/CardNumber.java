package model.entities;

import java.util.Date;

public class CardNumber {
	private int cardNumber;
	private String banner;
	private Date dueDate;
	private int securityCode;
	private String cardholderName;
	
	public CardNumber(int cardNumber, String banner, Date dueDate, int securityCode, String cardholderName) {
		super();
		this.cardNumber = cardNumber;
		this.banner = banner;
		this.dueDate = dueDate;
		this.securityCode = securityCode;
		this.cardholderName = cardholderName;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
}
