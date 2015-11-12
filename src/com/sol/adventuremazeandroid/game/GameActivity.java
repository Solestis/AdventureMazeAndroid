package com.sol.adventuremazeandroid.game;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}
	
	public void generateMaze(View view) {
		int mazeX = 12;
		int mazeY = 12;
		maze = new Maze(mazeX, mazeY);
		showMaze();
	}
	
	public void showMaze() {
		TileAdapter adapter = new TileAdapter(this, maze.getTileList());
		GridView mazeGrid = (GridView) findViewById(R.id.tileGrid);
		mazeGrid.setNumColumns(maze.getX());
		mazeGrid.setOnItemClickListener(this);
		mazeGrid.setAdapter(adapter);
		
		player = new Player("Sol");
		player.setLocation(maze.getTileList().get(0));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Tile clickedTile = maze.getTileList().get(position);
		player.move(clickedTile);
		clickedTile.onClick();
	}
}
