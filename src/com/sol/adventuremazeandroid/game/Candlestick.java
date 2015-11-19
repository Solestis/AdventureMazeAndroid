package com.sol.adventuremazeandroid.game;

import com.sol.adventuremazeandroid.R;

public class Candlestick extends Tool {

	public Candlestick() {
		type = Type.Candlestick;
		viewLayout = R.layout.candle_view;
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
