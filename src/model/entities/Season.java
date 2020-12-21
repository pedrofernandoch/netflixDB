package model.entities;

public class Season {
	private int serie;
	private int number;
	private String title;
	private int episodesQuantity;
	
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getEpisodesQuantity() {
		return episodesQuantity;
	}
	public void setEpisodesQuantity(int episodesQuantity) {
		this.episodesQuantity = episodesQuantity;
	}
	
}
