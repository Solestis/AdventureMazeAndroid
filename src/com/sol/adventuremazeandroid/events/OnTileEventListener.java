package com.sol.adventuremazeandroid.events;

import com.sol.adventuremazeandroid.game.Tile;

public interface OnTileEventListener {
	public void onTileEvent(Tile sourceTile);
	public void onExitEvent(Tile sourceTile);
	public void onItemPickupEvent(Tile sourceTile);
}
