package com.sol.adventuremazeandroid.game;

import java.lang.reflect.Constructor;

public abstract class Tool {
		
	public static enum Type {
		Candlestick;
	}
	
	public static Tool createTool(Type type) {
		Class<?> toolClass;
		Tool newTool = null;
		try {
			toolClass = Class.forName("com.sol.adventuremazeandroid.game." + type.toString());
			Constructor<?> constructor = toolClass.getConstructor();
			newTool = (Tool)constructor.newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return newTool;
	}
	
	protected Player player;		public void setPlayer(Player _player) {player = _player;}
	protected Type type;			public Type getType() {return type;}
	protected int viewLayout;		public int getViewLayout() {return viewLayout;}
	protected int uses = 0;
	protected boolean stackable;	public boolean isStackable() {return stackable;}
	
	public Tool() {}
	
	public void applyPassiveEffect(){}
	
	public void useTool() {
		if(uses > 0) {
			uses--;
			if(uses == 0) {
				player.removeTool(this);
			}
		}
	}
	
	public void addUse() {
		uses++;
	}
}
