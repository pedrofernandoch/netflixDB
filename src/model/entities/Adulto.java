package model.entities;

public class Adulto {
	private String alias;
	private int usuario;
	private int qualidStreaming;
	private int legenda;
	private int idioma;
	
	public Adulto(String alias, int usuario, int qualidStreaming, int legenda, int idioma) {
		super();
		this.alias = alias;
		this.usuario = usuario;
		this.qualidStreaming = qualidStreaming;
		this.legenda = legenda;
		this.idioma = idioma;
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
	public int getQualidStreaming() {
		return qualidStreaming;
	}
	public void setQualidStreaming(int qualidStreaming) {
		this.qualidStreaming = qualidStreaming;
	}
	public int getLegenda() {
		return legenda;
	}
	public void setLegenda(int legenda) {
		this.legenda = legenda;
	}
	public int getIdioma() {
		return idioma;
	}
	public void setIdioma(int idioma) {
		this.idioma = idioma;
	}
	
}
