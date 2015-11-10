package com.sol.adventuremazeandroid;

public class Tile {

	private int identifier;
	
	public Tile() {
		this(0);
	}
	
	public Tile(int _identifier) {
		identifier = _identifier;
	}

	public int getIdentifier() {
		return identifier;
	}
	
	@Override
	public String toString() {
		return stringNorth() + "\n" + stringWest();
	}
	
	public String stringNorth() {
		return (identifier & 1) == 0 ? "+---" : "+   ";
	}
	
	public String stringWest() {
		return (identifier & 8) == 0 ? "|   " : "    ";
	}

}
