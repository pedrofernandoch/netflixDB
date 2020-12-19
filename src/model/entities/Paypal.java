package model.entities;

public class Paypal {
	private int usuario;
	private String email;
	private String senha;
	
	public Paypal(int usuario, String email, String senha) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
