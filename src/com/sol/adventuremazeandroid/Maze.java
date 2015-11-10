package com.sol.adventuremazeandroid;

public class Maze {

	private Tile[][] tileMatrix;
	private int x;
	private int y;
	
	public Maze(int _x, int _y) {
		x = _x;
		y = _y;
		tileMatrix = new Tile[x][y];
		
		MazeGenerator mazeGenerator = new MazeGenerator(x,y);
		int[][] mazeIntArray = mazeGenerator.getMaze();
		
		for(int aX = 0; aX < mazeIntArray.length; aX++) {
			int[] intArray = mazeIntArray[aX];
			for(int aY = 0; aY < intArray.length; aY++) {
				int tileInt = intArray[aY];
				tileMatrix[aX][aY] = new Tile(tileInt);
			}
		}
	}
	
	public void printMazeInts() {
		for(int i = 0; i < tileMatrix.length; i++) {
			for(Tile[] tile : tileMatrix) {
				System.out.print(tile[i]+ " ");
			}
			System.out.println();
		}
	}
	
	public void display() {
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				tileMatrix[j][i].printNorth();
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				tileMatrix[j][i].printWest();
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
}
