package model.enums;

public enum LogActivities {
	CREATE("Criando"),
	READ("Lendo"),
	UPDATE("Atualizando"),
	DELETE("Deletando"),	
	ERROR("Erro");

	private String description;

	LogActivities(String desc) {
		this.description = desc;
	}

	public String getLogDescription() {
		return description;
	}

}
