package com.sol.adventuremazeandroid.game;

public class Player {
	
	//private Tile location;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	public void move(String direction) {
		switch (direction) {
		case "up":
			System.out.println("moved up");
			break;
		case "down":
			System.out.println("moved down");
			break;
		case "left":
			System.out.println("moved left");
			break;
		case "right":
			System.out.println("moved right");
			break;
		default:
			break;
	}
	}

}
