package model.entities;

public class Actuation {
	private String CPF;
	private int media;
	
	public Actuation(String cPF, int media) {
		super();
		CPF = cPF;
		this.media = media;
	}

	public String toString() {
		return "To be implemented...";
	}

	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public int getMedia() {
		return media;
	}
	public void setMedia(int media) {
		this.media = media;
	}
}
