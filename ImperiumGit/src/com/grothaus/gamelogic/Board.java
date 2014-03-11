package com.grothaus.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	private State gameState;
	private ArrayList<Player> players;
	private Layout layout;
	private ArrayList<Item> itemStore; 
	
	public Board(ArrayList<Player> players, Layout layout){
		if(gameState == null){
			this.players = players;
			this.layout = layout;
			setItemStore(new ArrayList<Item>());
			initGame();
		}
	}
	public void initGame(){
		System.out.println("initialising game");
		fillColonys();
		fillCastles();
		fillRoads();
	}
	private void fillRoads() {
		System.out.println("Filling roads");
		ArrayList<Ressource> cost = new ArrayList<Ressource>(
				Arrays.asList(Ressource.WOOD, Ressource.HORSE
						));
		for(Player p : players){
			for(int i = 0; i < 10; i++){
				getItemStore().add(new Item("Strasse "+i+ " von " + p.getName(),1,cost));
			}
		}
		System.out.println("Itemstore has size: " +itemStore.size());
		
	}
	private void fillCastles() {
		System.out.println("Filling castles");
		ArrayList<Ressource> cost = new ArrayList<Ressource>(
				Arrays.asList(Ressource.FOOD, Ressource.FOOD, Ressource.GOLD,
						Ressource.GOLD,Ressource.GOLD,
						Ressource.GOLD, Ressource.IRON,
						Ressource.IRON,Ressource.WOOD,
						Ressource.WOOD, Ressource.HORSE, Ressource.HORSE
						));
		for(Player p : players){
			for(int i = 0; i < 4; i++){
				getItemStore().add(new Item("Burg "+i+ " von " + p.getName(),20,cost));
			}
		}
		System.out.println("Itemstore has size: " +itemStore.size());
		
	}
	private void fillColonys() {
		System.out.println("Filling colonys");
		ArrayList<Ressource> cost = new ArrayList<Ressource>(
				Arrays.asList(Ressource.FOOD, Ressource.WOOD, Ressource.GOLD, Ressource.GOLD, Ressource.IRON
						));
		for(Player p : players){
			for(int i = 0; i < 4; i++){
				getItemStore().add(new Item("Siedlung "+i+ " von " + p.getName(),5,cost));
			}
		}
		System.out.println("Itemstore has size: " +itemStore.size());
	}
	public ArrayList<Item> getItemStore() {
		return itemStore;
	}
	private void setItemStore(ArrayList<Item> itemStore) {
		this.itemStore = itemStore;
	}
}
