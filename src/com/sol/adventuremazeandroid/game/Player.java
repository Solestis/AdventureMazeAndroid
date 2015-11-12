package com.sol.adventuremazeandroid.game;

public class Player {
	
	private Tile location;	public Tile getLocation() {return location;}	public void setLocation(Tile location) {this.location = location; move(this.location);}
	private String name; 	public String getName() {return name;}
	
	public Player(String _name) {
		name = _name;
	}
	
	public void move(Tile tile) {
		if(tile != location) {
			location.setPlayerHere(false);
			location = tile;
		}
		location.setPlayerHere(true);
	}

}
