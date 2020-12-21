package model.entities;

public class Serie {
	private int id;
	private String title;
	private int releaseYear;
	private int seasonsQuantity;
	
	public Serie(int id, String title, int releaseYear, int seasonsQuantity) {
		super();
		this.id = id;
		this.title = title;
		this.releaseYear = releaseYear;
		this.seasonsQuantity = seasonsQuantity;
	}
	
	public String toString() {
		return "<" + Integer.toString(id) + "," + title + "," + Integer.toString(releaseYear) + "," + Integer.toString(seasonsQuantity) + ">";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getSeasonsQuantity() {
		return seasonsQuantity;
	}
	public void setSeasonsQuantity(int seasonsQuantity) {
		this.seasonsQuantity = seasonsQuantity;
	}
}
