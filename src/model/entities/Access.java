package model.entities;

import java.security.Timestamp;

public class Access {
	private int usuario;
	private String NomeDisp;
	private String IPv4;
	private Timestamp horaAcesso;
	
	public Access(int usuario, String nomeDisp, String iPv4, Timestamp horaAcesso) {
		super();
		this.usuario = usuario;
		NomeDisp = nomeDisp;
		IPv4 = iPv4;
		this.horaAcesso = horaAcesso;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getNomeDisp() {
		return NomeDisp;
	}
	public void setNomeDisp(String nomeDisp) {
		NomeDisp = nomeDisp;
	}
	public String getIPv4() {
		return IPv4;
	}
	public void setIPv4(String iPv4) {
		IPv4 = iPv4;
	}
	public Timestamp getHoraAcesso() {
		return horaAcesso;
	}
	public void setHoraAcesso(Timestamp horaAcesso) {
		this.horaAcesso = horaAcesso;
	}
}
