package com.sol.adventuremazeandroid.activities;

import java.util.ArrayList;

import com.sol.adventuremazeandroid.R;
import com.sol.adventuremazeandroid.events.OnTileEventListener;
import com.sol.adventuremazeandroid.game.Maze;
import com.sol.adventuremazeandroid.game.Player;
import com.sol.adventuremazeandroid.game.Tile;
import com.sol.adventuremazeandroid.view.TileAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;

public class GameActivity extends Activity implements OnTileEventListener {

	private Maze maze;
	private Player player;
	private TileAdapter gridContents;
	private GridView mazeGrid;
	private int levelNumber = 1;
	//private int stepCounter = 0;
	
	private boolean fullMazeGrid = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);
		mazeGrid = (GridView) findViewById(R.id.tileGrid);
		player = new Player("Sol");
		
		startGame(levelNumber);
	}
	
	public void startGame(int levelNumber) {
		int mazeX = 3 + levelNumber;
		int mazeY = 3 + levelNumber;
		maze = new Maze(mazeX, mazeY);
		maze.setOnTileEventListener(this);
		maze.generate();
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
		//mazeGrid.postInvalidate();
		//mazeGrid.requestLayout();
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
			//stepCounter++;
			if(!fullMazeGrid) {
				showMaze();
			}
			maze.updateView(player);
		}
	}

	@Override
	public void onExitEvent(Tile sourceTile) {
		System.out.println("onExitEvent called from " + sourceTile);
		levelNumber++;
		startGame(levelNumber);
	}

	@Override
	public void onItemPickupEvent(Tile sourceTile) {
		System.out.println("onItemPickupEvent called from " + sourceTile);
	}
}
