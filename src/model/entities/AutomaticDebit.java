package model.entities;

public class AutomaticDebit {
	private int user;
	private int CPF;
	private String name;
	private String lastName;
	private String bank;
	private int agency;
	private int account;
	
	public AutomaticDebit(int user, int CPF, String name, String lastName, String bank, int agency, int account) {
		super();
		this.user = user;
		this.CPF = CPF;
		this.name = name;
		this.lastName = lastName;
		this.bank = bank;
		this.agency = agency;
		this.account = account;
	}
	
	public String toString() {
		return "<" + Integer.toString(user) + "," + Integer.toString(CPF) + "," + name + "," + lastName + "," + bank + "," + Integer.toString(agency) + "," + Integer.toString(account) + ">";
	}
	
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getCPF() {
		return CPF;
	}
	public void setCPF(int CPF) {
		this.CPF = CPF;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getAgency() {
		return agency;
	}
	public void setAgency(int agency) {
		this.agency = agency;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
}
