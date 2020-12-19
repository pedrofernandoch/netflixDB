package model.entities;

public class Perfil {
	private String alias;
	private int usuario;
	private String tipo;
	
	public Perfil(String alias, int usuario, String tipo) {
		super();
		this.alias = alias;
		this.usuario = usuario;
		this.tipo = tipo;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
