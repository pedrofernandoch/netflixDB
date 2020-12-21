package model.entities;

public class Direction {
	private String CPF;
	private int media;
	private String job;
	
	public Direction(String cPF, int media, String job) {
		super();
		CPF = cPF;
		this.media = media;
		this.job = job;
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
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}	
}
