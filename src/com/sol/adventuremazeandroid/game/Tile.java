package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;
import com.sol.adventuremazeandroid.view.TileLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

public class Tile {
	
	public final int layoutID = R.layout.item_tile_plain;
	
	private int configuration;	public int getConfiguration() {return configuration;}
	private boolean[] walls;	public boolean[] getWalls(){return walls;}
	private boolean[] corners;	public boolean[] getCorners(){return corners;}
	private int x;				public int getX() {return x;}
	private int y;				public int getY() {return y;}
	private View view;			public View getView() {return view;}
	private boolean player;		public void setPlayer(boolean _playerHere) {player = _playerHere;}		public boolean hasPlayer() {return player;}
	private boolean active;		public boolean isActive() {return active;}								public void setActive() {active = true;}		public void setInactive() {active = false;}
	
	private Context viewContext;
	private TileLayout tileLayout;
	private View playerView;
	
	public Tile() {
		this(0, 0, 0);
	}
	
	public Tile(int _identifier, int _x, int _y) {
		configuration = _identifier;
		x = _x;
		y = _y;
		player = false;
		active = false;
		
		walls = new boolean[4];
		walls[0] = (configuration & 1) == 0 ? true : false;
		walls[1] = (configuration & 4) == 0 ? true : false;
		walls[2] = (configuration & 2) == 0 ? true : false;
		walls[3] = (configuration & 8) == 0 ? true : false;
		
		corners = new boolean[4];
		corners[0] = (walls[0] && walls[3]) ? true : false;
		corners[1] = (walls[0] && walls[1]) ? true : false;
		corners[2] = (walls[1] && walls[2]) ? true : false;
		corners[3] = (walls[2] && walls[3]) ? true : false;
	}
	
	public void setView(View _view) {
		view = _view;
		viewContext = view.getContext();
		tileLayout = (TileLayout) view.findViewById(R.id.tile_layout);
		
		if(walls[0]) LayoutInflater.from(viewContext).inflate(R.layout.north_wall, tileLayout, true);
		if(walls[1]) LayoutInflater.from(viewContext).inflate(R.layout.east_wall, tileLayout, true);
		if(walls[2]) LayoutInflater.from(viewContext).inflate(R.layout.south_wall, tileLayout, true);
		if(walls[3]) LayoutInflater.from(viewContext).inflate(R.layout.west_wall, tileLayout, true);
		
		if(corners[0]) LayoutInflater.from(viewContext).inflate(R.layout.nw_inner_corner, tileLayout, true);
		if(corners[1]) LayoutInflater.from(viewContext).inflate(R.layout.ne_inner_corner, tileLayout, true);
		if(corners[2]) LayoutInflater.from(viewContext).inflate(R.layout.se_inner_corner, tileLayout, true);
		if(corners[3]) LayoutInflater.from(viewContext).inflate(R.layout.sw_inner_corner, tileLayout, true);
		
		if(player) setPlayerHere();
	}
	
	public void updateView() {
		if (view != null) {
			if(active) {
				view.setVisibility(View.VISIBLE);
			} else {
				view.setVisibility(View.INVISIBLE);
			}
		}
	}
	
	public void setPlayerHere() {
		if (view != null) {
			player = true;
			playerView = LayoutInflater.from(viewContext).inflate(R.layout.player_view, tileLayout, false);
			tileLayout.addView(playerView);
		}
	}
	
	public void removePlayer() {
		if (view != null) {
			player = false;
			tileLayout.removeView(playerView);
			playerView = null;
		}
	}
	
	public void onClick(){
		//Can be overridden in subclasses.
	}
	
	@Override
	public String toString() {
		return "Tile at: " + x + "," + y;
	}
}
