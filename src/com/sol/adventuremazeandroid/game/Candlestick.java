package com.sol.adventuremazeandroid.game;

public class Candlestick extends Tool {

	public Candlestick() {
		type = Type.Candlestick;
		stackable = false;
	}

	@Override
	public void applyPassiveEffect() {
		player.addViewRadius(1);
	}

	@Override
	public void useTool() {
		//This tool has no "On use".
	}

}
