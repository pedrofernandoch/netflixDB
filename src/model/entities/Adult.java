package model.entities;

public class Adult {
	private String alias;
	private int user;
	private int qualitStreaming;
	private int subtitle;
	private int language;
	
	public Adult(String alias, int user, int qualitStreaming, int subtitle, int language) {
		super();
		this.alias = alias;
		this.user = user;
		this.qualitStreaming = qualitStreaming;
		this.subtitle = subtitle;
		this.language = language;
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getQualitStreaming() {
		return qualitStreaming;
	}
	public void setQualitStreaming(int qualitStreaming) {
		this.qualitStreaming = qualitStreaming;
	}
	public int getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(int subtitle) {
		this.subtitle = subtitle;
	}
	public int getLanguage() {
		return language;
	}
	public void setLanguage(int language) {
		this.language = language;
	}
	
}
