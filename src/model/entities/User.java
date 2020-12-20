package model.entities;

import java.util.Date;

public class User {
	private String name;
	private int CPF;
	private String email;
	private Date birthday;
	private int plan;
	
	public User(String name, int cPF, String email, Date birthday, int plan) {
		super();
		this.name = name;
		CPF = cPF;
		this.email = email;
		this.birthday = birthday;
		this.plan = plan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCPF() {
		return CPF;
	}
	public void setCPF(int cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	
}
