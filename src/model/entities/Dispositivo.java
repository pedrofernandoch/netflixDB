package model.entities;

public class Dispositivo {
	private int usuario;
	private String nome;
	private String sistOperacional;
	
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
