package model.entities;

public class Infantil {
	private String alias;
	private int usuario;
	private int faixaEtaria;
	private String adulto;
	
	public Infantil(String alias, int usuario, int faixaEtaria, String adulto) {
		super();
		this.alias = alias;
		this.usuario = usuario;
		this.faixaEtaria = faixaEtaria;
		this.adulto = adulto;
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
	public int getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(int faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	public String getAdulto() {
		return adulto;
	}
	public void setAdulto(String adulto) {
		this.adulto = adulto;
	}
}
