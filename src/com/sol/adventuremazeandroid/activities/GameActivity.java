package com.sol.adventuremazeandroid.activities;

import java.util.ArrayList;

import com.sol.adventuremazeandroid.R;
import com.sol.adventuremazeandroid.events.OnTileEventListener;
import com.sol.adventuremazeandroid.game.Candlestick;
import com.sol.adventuremazeandroid.game.ExitTile;
import com.sol.adventuremazeandroid.game.Maze;
import com.sol.adventuremazeandroid.game.Player;
import com.sol.adventuremazeandroid.game.Tile;
import com.sol.adventuremazeandroid.game.Tool;
import com.sol.adventuremazeandroid.game.ToolTile;
import com.sol.adventuremazeandroid.view.TileAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;

public class GameActivity extends Activity implements OnTileEventListener {

	private Maze maze;
	private Player player;
	private TileAdapter gridContents;
	private GridView mazeGrid;
	private LinearLayout toolBar;
	private int levelNumber = 1;
	
	private boolean fullMazeGrid = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);
		mazeGrid = (GridView) findViewById(R.id.tileGrid);
		toolBar = (LinearLayout) findViewById(R.id.playerToolBar);
		player = new Player("Sol");
		
		startGame(levelNumber);
	}
	
	public void startGame(int levelNumber) {
		int mazeX = 3 + levelNumber;
		int mazeY = 3 + levelNumber;
		maze = new Maze(mazeX, mazeY);
		maze.setOnTileEventListener(this);
		maze.generate(player);
		player.setLocation(maze.getStartTile());
		
		showMaze();
	}
	
	public void showMaze() {
		if(!fullMazeGrid) {
			ArrayList<Tile> visibleTiles = maze.getVisibleList(player);
			if(gridContents == null) {
				gridContents = new TileAdapter(this, visibleTiles);
			} else {
				gridContents.clear();
				gridContents.addAll(visibleTiles);
			}
			mazeGrid.setNumColumns(maze.getNumVisibleColumns());
			mazeGrid.setAdapter(gridContents);
		} else {
			gridContents = new TileAdapter(this, maze.getTilesArray());
			mazeGrid.setNumColumns(maze.getWidth());
			mazeGrid.setAdapter(gridContents);
		}
		maze.updateView(player);
//		gridContents.notifyDataSetChanged();
//		mazeGrid.invalidateViews();
//		mazeGrid.postInvalidate();
//		mazeGrid.requestLayout();
	}
	
	public void navBack(View view) {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void restartGame(View view) {
		startGame(levelNumber);
	}
	
	public void showAllTiles(View view) {
		maze.setAllTilesVisible();
	}
	
	public void toggleFullMazeGrid(View view) {
		fullMazeGrid = (fullMazeGrid) ? false : true;
		showMaze();
	}
	
	@Override
	public void onTileEvent(Tile sourceTile) {
		if(sourceTile.isActive()) {
			player.move(sourceTile);
			if(!fullMazeGrid) {
				showMaze();
			}
			maze.updateView(player);
		}
	}

	@Override
	public void onExitEvent(ExitTile sourceTile) {
		levelNumber++;
		startGame(levelNumber);
	}

	@Override
	public void onToolPickupEvent(ToolTile sourceTile) {
		Tool addedTool = player.addTool(sourceTile.pickupTool());
		if(addedTool != null) {
			System.out.println("Adding tool to toolbar!");
			View toolView = LayoutInflater.from(toolBar.getContext()).inflate(addedTool.getViewLayout(), toolBar, false);
			toolBar.addView(toolView);
		}
	}
}
