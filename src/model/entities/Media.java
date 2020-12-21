package model.entities;

public class Media {
	private int id;
	private String title;
	private int releaseYear;
	private String thumb;
	private int duration;
	private String synopsis;
	private int ageRate;
	private int evaluation;
	private int episodesQuantity;
	private int serie;
	private int season;
	
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
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public int getAgeRate() {
		return ageRate;
	}
	public void setAgeRate(int ageRate) {
		this.ageRate = ageRate;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public int getEpisodesQuantity() {
		return episodesQuantity;
	}
	public void setEpisodesQuantity(int episodesQuantity) {
		this.episodesQuantity = episodesQuantity;
	}
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	
}
