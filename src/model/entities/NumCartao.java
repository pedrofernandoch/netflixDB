package model.entities;

import java.util.Date;

public class NumCartao {
	private int numCartao;
	private String bandeira;
	private Date dataVenc;
	private int codSeg;
	private String nomeTitular;
	
	public int getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(int numCartao) {
		this.numCartao = numCartao;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public Date getDataVenc() {
		return dataVenc;
	}
	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}
	public int getCodSeg() {
		return codSeg;
	}
	public void setCodSeg(int codSeg) {
		this.codSeg = codSeg;
	}
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
}
