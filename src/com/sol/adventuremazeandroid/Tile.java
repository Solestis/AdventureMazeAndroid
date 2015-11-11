package com.sol.adventuremazeandroid;

public class Tile {

	private int identifier;
	private boolean[] walls = {true, false, false, true};
	
	public Tile() {
		this(0);
	}
	
	public Tile(int _identifier) {
		identifier = _identifier;
		walls[0] = (identifier & 1) == 0 ? true : false;
		walls[3] = (identifier & 8) == 0 ? true : false;
	}

	public int getIdentifier() {
		return identifier;
	}
	
	@Override
	public String toString() {
		return stringNorth() + "\n" + stringWest();
		//return Integer.toString(identifier);
	}
	
	public boolean[] getWalls(){
		return walls;
	}
	
	public void setEastWall(boolean isWall) {
		walls[1] = isWall;
	}
	
	public void setSouthWall(boolean isWall) {
		walls[2] = isWall;
	}
	
	public String stringNorth() {
		return (identifier & 1) == 0 ? "+---" : "+   ";
	}
	
	public String stringWest() {
		return (identifier & 8) == 0 ? "|   " : "    ";
	}

}
