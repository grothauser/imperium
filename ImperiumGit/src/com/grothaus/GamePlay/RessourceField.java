package com.grothaus.GamePlay;

import java.util.Map;

public class RessourceField  {
	private String ressourceName;
	private Map<Player, String> players;
	private int luckyNumber;
	
	public RessourceField(String ressourceName, int luckyNumber, Map<Player, String> players){
		this.setRessourceName(ressourceName);
		this.setLuckyNumber(luckyNumber);
		this.setPlayers(players);
	}

	public String getRessourceName() {
		return ressourceName;
	}

	public void setRessourceName(String ressourceName) {
		this.ressourceName = ressourceName;
	}

	public int getLuckyNumber() {
		return luckyNumber;
	}

	public void setLuckyNumber(int luckyNumber) {
		this.luckyNumber = luckyNumber;
	}

	public Map<Player, String> getPlayers() {
		return players;
	}

	private void setPlayers(Map<Player, String> players2) {
		this.players = players2;
	}
	
	public void addPlayer(Player player, String colony){
		if(colony == "colony"){
			players.put(player,"colony");
		}
		else if(colony == "castle"){
			players.put(player, "castle");
		}
		
	}

}
