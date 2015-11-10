package com.sol.adventuremazeandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Maze maze;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void generateMaze(View view) {
		maze = new Maze(10, 10);
		//TextView mazeText = (TextView) findViewById(R.id.mazeText);
		//mazeText.setText(maze.toString());
		showMazeGrid();
	}
	
	public void showMazeGrid() {
		ArrayAdapter<Tile> adapter = new ArrayAdapter<Tile>(this, android.R.layout.simple_list_item_1, maze.getTilesArray());
		GridView mazeGrid = (GridView) findViewById(R.id.mazeGrid);
		mazeGrid.setNumColumns(maze.getY());
		mazeGrid.setAdapter(adapter);
	}
}
