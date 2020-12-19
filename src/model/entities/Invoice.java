package model.entities;

import java.util.Date;

public class Invoice {
	private int codFatura;
	private Date periodo;
	private int usuario;
	private int plano;
	
	public Invoice(int codFatura, Date periodo, int usuario, int plano) {
		super();
		this.codFatura = codFatura;
		this.periodo = periodo;
		this.usuario = usuario;
		this.plano = plano;
	}
	public int getCodFatura() {
		return codFatura;
	}
	public void setCodFatura(int codFatura) {
		this.codFatura = codFatura;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getPlano() {
		return plano;
	}
	public void setPlano(int plano) {
		this.plano = plano;
	}
}
