package model.entities;

public class Director {
	private String CPF;
	private String name;
	
	public Director(String cPF, String name) {
		super();
		CPF = cPF;
		this.name = name;
	}
	
	public String toString() {
		return "<" + CPF + "," + name + ">";
	}
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
