package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;

import android.graphics.Point;
import android.view.View;

public class Tile {

	private int listIndex;		public int getListIndex() {return listIndex;}			public void setListIndex(int index) {listIndex = index;}
	private int configuration;	public int getConfiguration() {return configuration;}
	private boolean[] walls;	public boolean[] getWalls(){return walls;}
	private Point location;		public Point getLocation() {return location;}
	private View view;			public View getView() {return view;}					public void setView(View view) {this.view = view;}
	private boolean player;		public boolean hasPlayer() {return player;}
	
	public Tile() {
		this(0, 0, 0);
	}
	
	public Tile(int _identifier, int _x, int _y) {
		configuration = _identifier;
		location = new Point(_x, _y);
		player = false;
		
		walls = new boolean[4];
		walls[0] = (configuration & 1) == 0 ? true : false;
		walls[1] = (configuration & 4) == 0 ? true : false;
		walls[2] = (configuration & 2) == 0 ? true : false;
		walls[3] = (configuration & 8) == 0 ? true : false;
	}
	
	public void setPlayerHere(boolean playerVisible) {
		if (view != null) {
			View playerView = (View)view.findViewById(R.id.player);
			if(playerVisible) {
				playerView.setVisibility(View.VISIBLE);
			} else {
				playerView.setVisibility(View.INVISIBLE);
			}
		}
		player = playerVisible;
	}
	
	public void onClick(){
		//System.out.println("Clicked on Tile:\n" + toString());
	}
	
	@Override
	public String toString() {
		String north = (configuration & 1) == 0 ? "+---+" : "+   +";
		String west = (configuration & 8) == 0 ? "|   " : "    ";
		String east = (configuration & 4) == 0 ? "|" : " ";
		String south = (configuration & 2) == 0 ? "+---+" : "+   +";
		return north + "\n" + west + east + "\n" + south;
	}
}
