package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;
import android.view.View;

public class Tile {

	private int configuration;	public int getConfiguration() {return configuration;}
	private boolean[] walls;	public boolean[] getWalls(){return walls;}
	private int x;				public int getX() {return x;}
	private int y;				public int getY() {return y;}
	private View view;			public View getView() {return view;}
	private boolean playerHere;	public void setPlayerHere(boolean _playerHere) {playerHere = _playerHere;}	public boolean hasPlayer() {return playerHere;}
	private boolean active;		public boolean isActive() {return active;}									public void setActive() {active = true;}		public void setInactive() {active = false;}
	
	private View northWall;
	private View eastWall;
	private View southWall;
	private View westWall;
	private View playerView;
	
	public Tile() {
		this(0, 0, 0);
	}
	
	public Tile(int _identifier, int _x, int _y) {
		configuration = _identifier;
		x = _x;
		y = _y;
		playerHere = false;
		active = false;
		
		walls = new boolean[4];
		walls[0] = (configuration & 1) == 0 ? true : false;
		walls[1] = (configuration & 4) == 0 ? true : false;
		walls[2] = (configuration & 2) == 0 ? true : false;
		walls[3] = (configuration & 8) == 0 ? true : false;
	}
	
	public void setView(View _view) {
		view = _view;
		northWall = view.findViewById(R.id.northWall);
		eastWall = view.findViewById(R.id.eastWall);
		southWall = view.findViewById(R.id.southWall);
		westWall = view.findViewById(R.id.westWall);
		playerView = view.findViewById(R.id.player);
		northWall.setVisibility((walls[0]) ? View.VISIBLE : View.INVISIBLE);
		eastWall.setVisibility((walls[1]) ? View.VISIBLE : View.INVISIBLE);
		southWall.setVisibility((walls[2]) ? View.VISIBLE : View.INVISIBLE);
		westWall.setVisibility((walls[3]) ? View.VISIBLE : View.INVISIBLE);
	}
	
	public void updateView() {
		if (view != null) {
			if(active) {
				view.setVisibility(View.VISIBLE);
			} else {
				view.setVisibility(View.INVISIBLE);
			}
			if(playerHere) {
				playerView.setVisibility(View.VISIBLE);
			} else {
				playerView.setVisibility(View.INVISIBLE);
			}
		}
	}
	
	public void onClick(){
		//System.out.println("Clicked on Tile:\n" + toString());
	}
	
	@Override
	public String toString() {
//		String north = (configuration & 1) == 0 ? "+---+" : "+   +";
//		String west = (configuration & 8) == 0 ? "|   " : "    ";
//		String east = (configuration & 4) == 0 ? "|" : " ";
//		String south = (configuration & 2) == 0 ? "+---+" : "+   +";
//		return north + "\n" + west + east + "\n" + south;
		return "Tile at: " + x + "," + y;
	}
}
