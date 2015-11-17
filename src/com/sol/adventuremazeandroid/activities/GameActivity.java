package com.sol.adventuremazeandroid.activities;

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
	private int stepCounter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);
		mazeGrid = (GridView) findViewById(R.id.tileGrid);
		
		startGame();
	}
	
	public void startGame() {
		int mazeX = 7;
		int mazeY = 7;
		maze = new Maze(mazeX, mazeY);
		maze.setOnTileEventListener(this);
		maze.generate();
		
		player = new Player("Sol");
		player.setLocation(maze.getStartTile());
		
		showMaze();
	}
	
	public void showMaze() {
//		ArrayList<Tile> visibleTiles = maze.getVisibleList(player);
//		if(gridContents == null) {
//			gridContents = new TileAdapter(this, visibleTiles);
//		} else {
//			gridContents.clear();
//			gridContents.addAll(visibleTiles);
//		}
//		mazeGrid.setNumColumns(maze.getNumVisibleColumns());
//		mazeGrid.setAdapter(gridContents);
		gridContents = new TileAdapter(this, maze.getTilesArray());
		mazeGrid.setNumColumns(maze.getWidth());
		mazeGrid.setAdapter(gridContents);
		maze.updateView(player);
	}
	
	public void navBack(View view) {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void restartGame(View view) {
		startGame();
	}
	
	public void showAllTiles(View view) {
		maze.setAllTilesVisible();
	}
	
	@Override
	public void onTileEvent(Tile sourceTile) {
		if(sourceTile.isActive()) {
			player.move(sourceTile);
			stepCounter++;
			//showMaze();
			maze.updateView(player);
		}
	}

	@Override
	public void onExitEvent(Tile sourceTile) {
		System.out.println("onExitEvent called from " + sourceTile);
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra("steps", stepCounter);
		startActivity(intent);
	}

	@Override
	public void onItemPickupEvent(Tile sourceTile) {
		System.out.println("onItemPickupEvent called from " + sourceTile);
	}
}
