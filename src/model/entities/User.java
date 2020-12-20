package model.entities;

import java.util.Date;

import javafx.scene.control.CheckBox;

public class User {
	private String name;
	private String cpf;
	private String email;
	private Date dateOfBirth;
	private int plan;
	private CheckBox checkBox = new CheckBox();
	
	public User(String name, String cpf, String email, Date dateOfBirth, int plan) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.plan = plan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
}
