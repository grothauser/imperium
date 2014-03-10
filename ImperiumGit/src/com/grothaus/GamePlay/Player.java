package com.grothaus.GamePlay;

import java.util.HashMap;
import java.util.Map;

public class Player {
	private String name;
	private Map<String, Integer> ressources;
	
	public Player(String name){
		ressources = new HashMap<String, Integer>();
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public void addRessource(String ressourceName) {
		if(ressourceName != "Nothing"){
			int oldValue = ressources.get(ressourceName) == null ? 0 :  ressources.get(ressourceName).intValue();
			ressources.put(ressourceName, oldValue + 1);
		}
		
	}
	public Map<String, Integer> getRessources(){
		return ressources;
	}
}
