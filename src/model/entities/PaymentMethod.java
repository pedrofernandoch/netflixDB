package model.entities;

public class FormaDePagamento {
	private int usuario;
	private String tipo;
	
	public FormaDePagamento(int usuario, String tipo) {
		super();
		this.usuario = usuario;
		this.tipo = tipo;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
