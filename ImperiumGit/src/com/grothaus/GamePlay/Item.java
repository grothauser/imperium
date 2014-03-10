package com.grothaus.GamePlay;

import java.util.ArrayList;

public class Item {
	private int points;
	private  ArrayList<Ressource> cost;
	private String name;
	public Item(String name, int points, ArrayList<Ressource> cost) {
		setName(name);
		setCost(cost);
		setPoints(points);
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public  ArrayList<Ressource> getCost() {
		return cost;
	}
	public void setCost(ArrayList<Ressource> cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
