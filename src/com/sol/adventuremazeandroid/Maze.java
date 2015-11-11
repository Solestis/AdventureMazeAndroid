package com.sol.adventuremazeandroid;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class Maze {

	private Tile[][] tileMatrix;
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

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
				Tile tile = new Tile(tileInt);
				if(aX == mazeIntArray.length) {
					tile.setEastWall(true);
				}
				if(aY == mazeIntArray.length) {
					tile.setSouthWall(true);
				}
				tileMatrix[aX][aY] = tile;
			}
		}
	}
	
	public void printMazeInts() {
		for(int i = 0; i < tileMatrix.length; i++) {
			for(Tile[] tile : tileMatrix) {
				System.out.print(tile[i].getIdentifier()+ " ");
			}
			System.out.println();
		}
	}
	
	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				out.print(tileMatrix[j][i].stringNorth());
			}
			out.println("+");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				out.print(tileMatrix[j][i].stringWest());
			}
			out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			out.print("+---");
		}
		out.println("+");
		return writer.toString();
	}
	
	public ArrayList<Tile> getTilesArray() {
		ArrayList<Tile> result = new ArrayList<Tile>();
		
		for(int i = 0; i < x; i++) {
			for(Tile[] tileArray : tileMatrix) {
				result.add(tileArray[i]);
			}
		}
		
		return result;
	}
}
