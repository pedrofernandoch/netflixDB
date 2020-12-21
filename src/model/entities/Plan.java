package model.entities;

public class Plan {
	private int id;
	private String name;
	private float value;
	private int maxQuality;
	private int screensQuantity;
	private int maxProfileNumber;
	
	public Plan(int id, String name, float value, int maxQuality, int screensQuantity, int maxProfileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.maxQuality = maxQuality;
		this.screensQuantity = screensQuantity;
		this.maxProfileNumber = maxProfileNumber;
	}
	
	public String toString() {
		return "<" + Integer.toString(id) + "," + name + "," + Float.toString(value) + "," + Integer.toString(maxQuality) + "," + Integer.toString(screensQuantity)  + "," + Integer.toString(maxProfileNumber) + ">";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getMaxQuality() {
		return maxQuality;
	}
	public void setMaxQuality(int maxQuality) {
		this.maxQuality = maxQuality;
	}
	public int getScreensQuantity() {
		return screensQuantity;
	}
	public void setScreensQuantity(int screensQuantity) {
		this.screensQuantity = screensQuantity;
	}
	public int getMaxProfileNumber() {
		return maxProfileNumber;
	}
	public void setMaxProfileNumber(int maxProfileNumber) {
		this.maxProfileNumber = maxProfileNumber;
	}
}
