package model.entities;

public class MediaAudio {
	private int media;
	private int language;
	private boolean original;
	
	public MediaAudio(int media, int language, boolean original) {
		super();
		this.media = media;
		this.language = language;
		this.original = original;
	}
	
	public String toString() {
		return "To be implemented...";
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
	public boolean isOriginal() {
		return original;
	}
	public void setOriginal(boolean original) {
		this.original = original;
	}
}
