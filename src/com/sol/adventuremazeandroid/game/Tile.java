package com.sol.adventuremazeandroid.game;

public class Tile {

	private int identifier; public int getIdentifier() {return identifier;}
	
	private boolean[] walls; public boolean[] getWalls(){return walls;}

	public Tile() {
		this(0);
	}
	
	public Tile(int _identifier) {
		identifier = _identifier;
		walls = new boolean[4];
		walls[0] = (identifier & 1) == 0 ? true : false;
		walls[1] = (identifier & 4) == 0 ? true : false;;
		walls[2] = (identifier & 2) == 0 ? true : false;
		walls[3] = (identifier & 8) == 0 ? true : false;
	}


	
	public void onClick(){
		System.out.println("Clicked on Tile:\n" + toString());
	}
	
	@Override
	public String toString() {
		return ((identifier & 1) == 0 ? "+---" : "+   ") + "\n" + ((identifier & 8) == 0 ? "|   " : "    ");
	}
	
	public void setEastWall(boolean isWall) {
		walls[1] = isWall;
	}
	
	public void setSouthWall(boolean isWall) {
		walls[2] = isWall;
	}
}
