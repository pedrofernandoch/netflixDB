package model.entities;

public class MediaSubtitle {
	private int media;
	private int language;
	
	public MediaSubtitle(int media, int language) {
		super();
		this.media = media;
		this.language = language;
	}
	
	public String toString() {
		return "<" + Integer.toString(media) + "," + Integer.toString(language) + ">";
	}
	
	public int getMedia() {
		return media;
	}
	public void setMedia(int media) {
		this.media = media;
	}
	public int getLanguage() {
		return language;
	}
	public void setLanguage(int language) {
		this.language = language;
	}
}
