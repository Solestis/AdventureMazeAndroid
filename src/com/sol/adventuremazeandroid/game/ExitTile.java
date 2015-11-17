package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;

import android.view.LayoutInflater;
import android.view.View;

public class ExitTile extends Tile {

	public ExitTile() {
		this(0, 0, 0);
	}

	public ExitTile(int _identifier, int _x, int _y) {
		super(_identifier, _x, _y);
	}
	
	@Override
	public void setView(View _view) {
		super.setView(_view);
		LayoutInflater.from(viewContext).inflate(R.layout.exit, tileLayout, true);
	}
	
	@Override
	public void onClick(View view){
		super.onClick(view);
		onTileEventListener.onExitEvent(this);
	}
	
	@Override
	public String toString() {
		return "ExitTile at: " + x + "," + y;
	}

}
