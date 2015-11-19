package com.sol.adventuremazeandroid.game;

import java.util.HashMap;

import com.sol.adventuremazeandroid.R;

public class Player {
	
	public final int layoutID = R.layout.player_view;
	
	private Tile location;						public Tile getLocation() {return location;}		public void setLocation(Tile location) {this.location = location; move(this.location);}
	private String name; 						public String getName() {return name;}
	private int viewRadius = 1;					public int getViewRadius() {return viewRadius;}
	private HashMap<Tool.Type, Tool> tools;		public HashMap<Tool.Type, Tool> getTools() {return tools;}
	
	public Player(String _name) {
		name = _name;
		tools = new HashMap<Tool.Type, Tool>();
	}
	
	public void move(Tile tile) {
		if(tile != location) {
			location.removePlayer();
			location = tile;
		}
		location.setPlayerHere();
	}
	
	public void addTool(Tool newTool) {
		if(!tools.containsKey(newTool.type)) {
			newTool.setPlayer(this);
			newTool.applyPassiveEffect();
			tools.put(newTool.getType(), newTool);
		} else if(newTool.isStackable()) {
			newTool.addUse();
		}
	}
	
	public void removeTool(Tool newTool) {
		tools.remove(newTool.getType());
	}
	
	public void addViewRadius(int viewIncrease) {
		viewRadius += viewIncrease;
	}
}
