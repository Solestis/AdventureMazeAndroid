package com.sol.adventuremazeandroid.game;

import java.util.ArrayList;

import com.sol.adventuremazeandroid.R;
import com.sol.adventuremazeandroid.view.TileAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GameActivity extends Activity implements OnItemClickListener {

	private Maze maze;
	private Player player;
	private TileAdapter gridContents;
	private GridView mazeGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		mazeGrid = (GridView) findViewById(R.id.tileGrid);
		mazeGrid.setOnItemClickListener(this);
		
		startGame();
	}
	
	public void startGame() {
		int mazeX = 7;
		int mazeY = 7;
		maze = new Maze(mazeX, mazeY);
		
		player = new Player("Sol");
		player.setLocation(maze.getStartTile());
		
		showMaze();
	}
	
	public void showMaze() {
		ArrayList<Tile> visibleTiles = maze.getVisibleList(player);
		gridContents = new TileAdapter(this, visibleTiles);
		mazeGrid.setNumColumns(maze.getNumVisibleColumns());
		mazeGrid.setAdapter(gridContents);
		player.getLocation().setActive();
		maze.setVisibility(player.getLocation(), player.getViewRadius(), true);
	}
	
	public void restartGame(View view) {
		startGame();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Tile clickedTile = gridContents.getItem(position);
		if(clickedTile.isActive()) {
			//System.out.println(clickedTile + " is active, moving player.");
			player.move(clickedTile);
			showMaze();
			clickedTile.onClick();
		}
	}
}
