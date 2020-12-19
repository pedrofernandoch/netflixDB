package model.entities;

import java.util.Date;

public class Usuario {
	private String nome;
	private int CPF;
	private String email;
	private Date dataNasc;
	private int plano;
	
	public Usuario(String nome, int cPF, String email, Date dataNasc, int plano) {
		super();
		this.nome = nome;
		CPF = cPF;
		this.email = email;
		this.dataNasc = dataNasc;
		this.plano = plano;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCPF() {
		return CPF;
	}
	public void setCPF(int cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public int getPlano() {
		return plano;
	}
	public void setPlano(int plano) {
		this.plano = plano;
	}
	
}
