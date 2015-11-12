package com.sol.adventuremazeandroid;

import java.util.ArrayList;

import com.sol.adventuremazeandroid.view.TileAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemClickListener {

	private Maze maze;
	private ArrayList<Tile> tilesArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void generateMaze(View view) {
		maze = new Maze(12, 12);
		tilesArray = maze.getTileList();
		System.out.println(maze);
		maze.printMazeInts();
		showMazeGrid();
	}
	
	public void showMazeGrid() {
		TileAdapter adapter = new TileAdapter(this, tilesArray);
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
		tilesArray.get(position).onClick();
	}
}
