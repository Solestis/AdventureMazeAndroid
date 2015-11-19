package com.sol.adventuremazeandroid.events;

import com.sol.adventuremazeandroid.game.ExitTile;
import com.sol.adventuremazeandroid.game.Tile;
import com.sol.adventuremazeandroid.game.ToolTile;

public interface OnTileEventListener {
	public void onTileEvent(Tile sourceTile);
	public void onExitEvent(ExitTile sourceTile);
	public void onToolPickupEvent(ToolTile sourceTile);
}
