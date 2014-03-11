package com.grothaus.gamelogic;

public class Pair implements Comparable<Pair> {
	private int x;
	private int y;

	public Pair(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public String toString(){
		return "[x: " + x + " y: " + y + "]";
	}

	public boolean equals(Pair o) {
		return this.getX() == o.getX() && this.getY() == o.getY() ? true : false;
	}

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		if(o.equals(this)){
			return 0;
		}
		if(o.getX() > this.getX()){
			return -1;
		}
		else{
			return 1;
		}
	}
	
	
}
