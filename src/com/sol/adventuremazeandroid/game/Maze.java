package com.sol.adventuremazeandroid.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.sol.adventuremazeandroid.events.OnTileEventListener;

import android.graphics.Point;

public class Maze {

	private MazeGenerator mazeGenerator;	
	private int width; 					public int getWidth() {return width;}
	private int height; 				public int getHeight() {return height;}
	private Tile startTile;				public Tile getStartTile() {return startTile;}
	private Tile endTile;				public Tile getEndTile() {return endTile;}
	private Tile[][] tileMatrix;
	private int numVisibleColumns = 0;	public int getNumVisibleColumns() {return numVisibleColumns;}
	private OnTileEventListener onTileEventListener;		public void setOnTileEventListener(OnTileEventListener listener) {onTileEventListener = listener;}

	public Maze(int _width, int _height) {
		width = _width;
		height = _height;
	}
	
	public void generate(HashMap<Tool.Type, Tool> playerTools) {
		tileMatrix = new Tile[width][height];
		mazeGenerator = new MazeGenerator(width,height);
		int[][] mazeIntArray = mazeGenerator.getMaze();
		
		for(int aX = 0; aX < width; aX++) {
			int[] intArray = mazeIntArray[aX];
			for(int aY = 0; aY < height; aY++) {
				int tileInt = intArray[aY];
				Tile tile = new Tile(tileInt, aX, aY);
				tile.setTileEventListener(onTileEventListener);
				tileMatrix[aX][aY] = tile;
			}
		}
		placeStartEndTiles();
		placeToolTiles(playerTools);
	}
	
	public void updateView(Player player) {
		setVisibility(player.getLocation(), player.getViewRadius(), true);
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
	
	public void setAllTilesVisible() {
		for(Tile[] tiles : tileMatrix) {
			for(Tile t : tiles) {
				t.setActive();
				t.updateView();
			}
		}
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
	
	private void placeStartEndTiles() {
		double halfWidth = width/2-1;
		double halfHeight = height/2-1;
		
		int randomX = (int)(Math.random()*halfWidth);
		int randomY = (int)(Math.random()*halfHeight);
		
		startTile = tileMatrix[randomX][randomY];
		startTile.setPlayer(true);
		
		int endTileX = width-randomX-1;
		int endTileY = height-randomY-1;
		int endTileConfig = tileMatrix[endTileX][endTileY].getConfiguration();
		endTile = new ExitTile(endTileConfig, endTileX, endTileY);
		endTile.setTileEventListener(onTileEventListener);
		tileMatrix[endTileX][endTileY] = endTile;
	}
	
	private void placeToolTiles(HashMap<Tool.Type, Tool> playerTools) {
		for(Tool.Type toolType : Tool.Type.values()) {
			if(!playerTools.containsKey(toolType)) {
				Point rc = getRandomCoordinates();
				ToolTile toolTile = new ToolTile(tileMatrix[rc.x][rc.y].getConfiguration(), rc.x, rc.y);
				toolTile.placeTool(Tool.createTool(toolType));
				toolTile.setTileEventListener(onTileEventListener);
				tileMatrix[rc.x][rc.y] = toolTile;
			}
		}
	}
	
	private void setVisibility(Tile tile, int visibilityRange, boolean isPlayerTile) {
		if(isPlayerTile) {
			for(Tile[] tiles : tileMatrix) {
				for(Tile t : tiles) {
					t.setInactive();
				}
			}
			tile.setActive();
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
					setVisibility(nextTile, range, false);
				}
			}
		}
	}
	
	private Point getRandomCoordinates() {
		Point resultPoint = new Point(0,0);
		
		resultPoint.x = (int)(Math.random()*width);
		resultPoint.y = (int)(Math.random()*height);
		
		return resultPoint;
	}
}
