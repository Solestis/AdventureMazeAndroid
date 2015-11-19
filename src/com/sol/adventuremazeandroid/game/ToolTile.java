package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;

import android.view.LayoutInflater;
import android.view.View;

public class ToolTile extends Tile {
	
	private Tool tool;
	private View toolView;

	public ToolTile() {
		super();
	}

	public ToolTile(int _identifier, int _x, int _y) {
		super(_identifier, _x, _y);
	}
	
	@Override
	public void setView(View view) {
		super.setView(view);
		if(tool != null) {
			toolView = LayoutInflater.from(viewContext).inflate(tool.getViewLayout(), tileLayout, false);
			tileLayout.addView(toolView);
		}
	}
	
	public void placeTool(Tool _tool) {
		tool = _tool;
	}
	
	public Tool pickupTool() {
		Tool _tool = tool;
		tool = null;
		
		tileLayout.removeView(toolView);
		toolView = null;
		
		return _tool;
	}
	
	@Override
	public void onClick(View view){
		if(tool != null) {
			onTileEventListener.onToolPickupEvent(this);
		}
		super.onClick(view);
	}

}
