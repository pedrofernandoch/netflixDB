package model.entities;

public class Device {
	private int usuario;
	private String nome;
	private String sistOperacional;
	
	public Device(int usuario, String nome, String sistOperacional) {
		super();
		this.usuario = usuario;
		this.nome = nome;
		this.sistOperacional = sistOperacional;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSistOperacional() {
		return sistOperacional;
	}
	public void setSistOperacional(String sistOperacional) {
		this.sistOperacional = sistOperacional;
	}
}
