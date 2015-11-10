package com.sol.adventuremazeandroid;

public class Tile {

	private int identifier;
	
	public Tile() {
		this(0);
	}
	
	public Tile(int _identifier) {
		identifier = _identifier;
	}
	
	@Override
	public String toString() {
		return Integer.toString(identifier);
	}

	public int getIdentifier() {
		return identifier;
	}
	
	public void printNorth() {
		System.out.print((identifier & 1) == 0 ? "+---" : "+   ");
	}
	
	public void printWest() {
		System.out.print((identifier & 8) == 0 ? "|   " : "    ");
	}

}
