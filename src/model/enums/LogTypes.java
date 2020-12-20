package model.enums;

public enum LogTypes {
	CRUD("CRUD"), ERROR("Error"), SYSTEM("Sistema"), CONNECTION("Conex�o");

	private String description;

	LogTypes(String desc) {
		this.description = desc;
	}

	public String getLogDescription() {
		return description;
	}
	
}
