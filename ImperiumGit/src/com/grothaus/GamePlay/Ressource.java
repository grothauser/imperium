package com.grothaus.GamePlay;

public enum Ressource {
	IRON("Eisen"), WOOD("Holz"), GOLD("Gold"), FOOD("Nahrung"), HORSE("Pferd");
	private String name;
	
	private Ressource(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
