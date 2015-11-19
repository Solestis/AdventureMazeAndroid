package com.sol.adventuremazeandroid.game;

import android.view.View;

public class ToolTile extends Tile {
	
	private Tool tool;

	public ToolTile() {
		super();
	}

	public ToolTile(int _identifier, int _x, int _y) {
		super(_identifier, _x, _y);
	}
	
	public void placeTool(Tool _tool) {
		tool = _tool;
		
	}
	
	public Tool pickupTool() {
		Tool _tool = tool; tool = null; return _tool;
	}
	
	@Override
	public void onClick(View view){
		if(tool != null) {
			onTileEventListener.onToolPickupEvent(this);
		}
		super.onClick(view);
	}

}
