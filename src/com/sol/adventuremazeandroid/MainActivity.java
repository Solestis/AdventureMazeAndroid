package com.sol.adventuremazeandroid;

import com.sol.adventuremazeandroid.adapters.TileAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class MainActivity extends Activity {

	private Maze maze;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void generateMaze(View view) {
		maze = new Maze(10, 10);
		System.out.println(maze);
		maze.printMazeInts();
		showMazeGrid();
	}
	
	public void showMazeGrid() {
		TileAdapter adapter = new TileAdapter(this, maze.getTilesArray());
		GridView mazeGrid = (GridView) findViewById(R.id.mazeGrid);
		mazeGrid.setNumColumns(maze.getX());
		mazeGrid.setAdapter(adapter);
	}
}
