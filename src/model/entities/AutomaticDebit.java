package model.entities;

public class DebitoAutomatico {
	private int usuario;
	private int CPF;
	private String nome;
	private String sobrenome;
	private String banco;
	private int agencia;
	private int conta;
	
	public DebitoAutomatico(int usuario, int cPF, String nome, String sobrenome, String banco, int agencia, int conta) {
		super();
		this.usuario = usuario;
		CPF = cPF;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getCPF() {
		return CPF;
	}
	public void setCPF(int cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
}
