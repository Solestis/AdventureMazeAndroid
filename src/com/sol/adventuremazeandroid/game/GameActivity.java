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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void generateMaze(View view) {
		maze = new Maze(12, 12);
		//System.out.println(maze);
		//maze.printMazeInts();
		showMazeGrid();
	}
	
	public void showMazeGrid() {
		TileAdapter adapter = new TileAdapter(this, maze.getTileList());
		GridView mazeGrid = (GridView) findViewById(R.id.mazeGrid);
		mazeGrid.setNumColumns(maze.getX());
		mazeGrid.setOnItemClickListener(this);
		mazeGrid.setAdapter(adapter);
	}
	
	public void movePlayer() {
		System.out.println("Moving Player");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		maze.getTileList().get(position).onClick();
	}
}
