package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;

public class Player {
	
	public final int layoutID = R.layout.player_view;
	
	private Tile location;			public Tile getLocation() {return location;}	public void setLocation(Tile location) {this.location = location; move(this.location);}
	private String name; 			public String getName() {return name;}
	private int viewRadius;			public int getViewRadius() {return viewRadius;}
	
	public Player(String _name) {
		name = _name;
		viewRadius = 2;
	}
	
	public void move(Tile tile) {
		if(tile != location) {
			location.removePlayer();
			location = tile;
		}
		location.setPlayerHere();
	}

}
