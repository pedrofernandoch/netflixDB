package model.entities;

import java.security.Timestamp;

public class Access {
	private int user;
	private String deviceName;
	private String IPv4;
	private Timestamp accessTime;
	
	public Access(int user, String deviceName, String IPv4, Timestamp accessTime) {
		super();
		this.user = user;
		this.deviceName = deviceName;
		this.IPv4 = IPv4;
		this.accessTime = accessTime;
	}
	
	public String toString() {
		return "To be implemented...";
	}
	
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getIPv4() {
		return IPv4;
	}
	public void setIPv4(String IPv4) {
		this.IPv4 = IPv4;
	}
	public Timestamp getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
	}
}
