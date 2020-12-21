package model.entities;

public class Genre {
	private String name;
	private String description;
	
	public Genre(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String toString() {
		return "To be implemented...";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
