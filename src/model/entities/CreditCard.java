package model.entities;

public class CreditCard {
	private int usuario;
	private int numCartao;
	
	public CreditCard(int usuario, int numCartao) {
		super();
		this.usuario = usuario;
		this.numCartao = numCartao;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(int numCartao) {
		this.numCartao = numCartao;
	}
}
