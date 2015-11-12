package com.sol.adventuremazeandroid;

import java.util.ArrayList;

public class Maze {

	private MazeGenerator mazeGenerator;
	
	private ArrayList<Tile> tileList; public ArrayList<Tile> getTileList() {return tileList;}
	private int x; public int getX() {return x;}
	private int y; public int getY() {return y;}

	public Maze(int _x, int _y) {
		x = _x;
		y = _y;
		
		tileList = new ArrayList<Tile>();
		generateMaze();	
	}
	
	private void generateMaze() {
		Tile[][] tileMatrix = new Tile[x][y];
		mazeGenerator = new MazeGenerator(x,y);
		int[][] mazeIntArray = mazeGenerator.getMaze();
		
		for(int aX = 0; aX < x; aX++) {
			int[] intArray = mazeIntArray[aX];
			for(int aY = 0; aY < y; aY++) {
				int tileInt = intArray[aY];
				Tile tile = new Tile(tileInt);
				if(aX == (x-1)) {
					tile.setEastWall(true);
				}
				if(aY == (y-1)) {
					tile.setSouthWall(true);
				}
				tileMatrix[aX][aY] = tile;
			}
		}
		for(int i = 0; i < x; i++) {
			for(Tile[] tileArray : tileMatrix) {
				tileList.add(tileArray[i]);
			}
		}
	}
	
	public void printMazeInts() {
		mazeGenerator.printMazeInts();
	}
	
	@Override
	public String toString() {
		return mazeGenerator.toString();
	}
}
