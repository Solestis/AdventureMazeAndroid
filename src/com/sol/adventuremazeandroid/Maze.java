package com.sol.adventuremazeandroid;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

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
	
	public Tile[] getTilesArray() {
		Tile[] firstArray = tileMatrix[0];
		int totalLength = firstArray.length;
		
		for (Tile[] array : tileMatrix) {
			totalLength += array.length;
		}
		
		Tile[] result = Arrays.copyOf(firstArray, totalLength);
		
		int offset = firstArray.length;
		for (Tile[] array : tileMatrix) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
}
