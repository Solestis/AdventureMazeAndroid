package com.sol.adventuremazeandroid.view;

import java.util.List;

import com.sol.adventuremazeandroid.R;
import com.sol.adventuremazeandroid.game.Tile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TileAdapter extends ArrayAdapter<Tile> {
	
	public TileAdapter(Context context, List<Tile> tiles) {
		super(context, 0, tiles);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tile tile = getItem(position);
		if(convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tile_plain, parent, false);	
		}
		convertView.setMinimumHeight(convertView.getWidth());
		boolean[] walls = tile.getWalls();
		if (!walls[0]) {
			View northWall = convertView.findViewById(R.id.northWall);
			northWall.setVisibility(View.INVISIBLE);
		}
		if(!walls[1]) {
			View eastWall = convertView.findViewById(R.id.eastWall);
			eastWall.setVisibility(View.INVISIBLE);
		}
		if(!walls[2]) {
			View southWall = convertView.findViewById(R.id.southWall);
			southWall.setVisibility(View.INVISIBLE);
		}
		if(!walls[3]) {
			View westWall = convertView.findViewById(R.id.westWall);
			westWall.setVisibility(View.INVISIBLE);
		}
		if(tile.hasPlayer()) {
			View player = convertView.findViewById(R.id.player);
			player.setVisibility(View.VISIBLE);
		}
		tile.setView(convertView);
		
		return convertView;	
	}
}
