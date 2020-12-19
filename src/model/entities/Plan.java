package model.entities;

public class Plan {
	private int id;
	private String nome;
	private float valor;
	private int qualidadeMax;
	private int qtdTelas;
	private int numMaxPerfis;
	
	public Plan(int id, String nome, float valor, int qualidadeMax, int qtdTelas, int numMaxPerfis) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.qualidadeMax = qualidadeMax;
		this.qtdTelas = qtdTelas;
		this.numMaxPerfis = numMaxPerfis;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getQualidadeMax() {
		return qualidadeMax;
	}
	public void setQualidadeMax(int qualidadeMax) {
		this.qualidadeMax = qualidadeMax;
	}
	public int getQtdTelas() {
		return qtdTelas;
	}
	public void setQtdTelas(int qtdTelas) {
		this.qtdTelas = qtdTelas;
	}
	public int getNumMaxPerfis() {
		return numMaxPerfis;
	}
	public void setNumMaxPerfis(int numMaxPerfis) {
		this.numMaxPerfis = numMaxPerfis;
	}
}
