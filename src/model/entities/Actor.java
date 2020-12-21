package model.entities;

public class Actor {
	private String CPF;
	private String name;
	
	public Actor(String cPF, String name) {
		super();
		CPF = cPF;
		this.name = name;
	}
	
	public String toString() {
		return "To be implemented...";
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
