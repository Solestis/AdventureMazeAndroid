package com.sol.adventuremazeandroid.game;

import java.util.ArrayList;

public class Maze {

	private MazeGenerator mazeGenerator;	
	private int width; 					public int getWidth() {return width;}
	private int height; 				public int getHeight() {return height;}
	private Tile startTile;				public Tile getStartTile() {return startTile;}
	private Tile[][] tileMatrix;
	private int numVisibleColumns = 0;	public int getNumVisibleColumns() {return numVisibleColumns;}

	public Maze(int _width, int _height) {
		width = _width;
		height = _height;
		generateMaze();	
	}
	
	private void generateMaze() {
		tileMatrix = new Tile[width][height];
		mazeGenerator = new MazeGenerator(width,height);
		int[][] mazeIntArray = mazeGenerator.getMaze();
		
		for(int aX = 0; aX < width; aX++) {
			int[] intArray = mazeIntArray[aX];
			for(int aY = 0; aY < height; aY++) {
				int tileInt = intArray[aY];
				Tile tile = new Tile(tileInt, aX, aY);
				tileMatrix[aX][aY] = tile;
			}
		}
		startTile = tileMatrix[3][3];
		startTile.setPlayer(true);
	}
	
	public void updateView(Player player) {
		setVisibility(player.getLocation(), player.getViewRadius());
		for(Tile tile : getTilesArray()) {
			tile.updateView();
		}
	}
	
	public ArrayList<Tile> getVisibleList(Player player) {
		int y = player.getLocation().getY();
		int x = player.getLocation().getX();
		int viewRadius = player.getViewRadius();
		
		ArrayList<Tile> visibleList = new ArrayList<Tile>();
		
		int minimumY = (y-viewRadius < 0) ? 0 : y-viewRadius;
		int maximumY = (y+viewRadius > (height-1)) ? (height-1) : y+viewRadius;
		int minimumX = (x-viewRadius < 0) ? 0 : x-viewRadius;
		int maximumX = (x+viewRadius > (width-1)) ? (width-1) : x+viewRadius;
		numVisibleColumns = maximumX - minimumX + 1;
		
		for(int vy = minimumY; vy <= maximumY; vy++) {
			for(int vx = minimumX; vx <= maximumX; vx++) {
				visibleList.add(tileMatrix[vx][vy]);
			}
		}
		
		return visibleList;
	}
	
	public void printMazeInts() {
		mazeGenerator.printMazeInts();
	}
	
	public ArrayList<Tile> getTilesArray() {
		ArrayList<Tile> result = new ArrayList<Tile>();
		
		for(int i = 0; i < width; i++) {
			for(Tile[] tileArray : tileMatrix) {
				result.add(tileArray[i]);
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return mazeGenerator.toString();
	}
	
	private void setVisibility(Tile tile, int visibilityRange) {
		for(Tile[] tiles : tileMatrix) {
			for(Tile t : tiles) {
				t.setInactive();
			}
		}
		
		boolean[] walls = tile.getWalls();
		int range = visibilityRange-1;
		
		for(int i = 0; i < walls.length; i++) {
			Tile nextTile = null;
			if(!walls[i]) {
				if(i==0) {
					nextTile = tileMatrix[tile.getX()][tile.getY()-1];
				}
				if(i==1) {
					nextTile = tileMatrix[tile.getX()+1][tile.getY()];
				}
				if(i==2) {
					nextTile = tileMatrix[tile.getX()][tile.getY()+1];
				}
				if(i==3) {
					nextTile = tileMatrix[tile.getX()-1][tile.getY()];
				}
				nextTile.setActive();
				if(nextTile != null && range > 0) {
					setVisibility(nextTile, range);
				}
			}
		}
	}
}
