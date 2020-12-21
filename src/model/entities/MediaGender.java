package model.entities;

public class MediaGender {
	private String gender;
	private int media;
	
	public MediaGender(String gender, int media) {
		super();
		this.gender = gender;
		this.media = media;
	}
	
	public String toString() {
		return "To be implemented...";
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getMedia() {
		return media;
	}
	public void setMedia(int media) {
		this.media = media;
	}
}
