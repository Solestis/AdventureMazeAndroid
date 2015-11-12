package com.sol.adventuremazeandroid.view;

import java.util.List;
import com.sol.adventuremazeandroid.Tile;
import com.sol.adventuremazeandroid.R;
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
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tile, parent, false);	
		}
		convertView.setMinimumHeight(convertView.getWidth());
		boolean[] walls = tile.getWalls();
		View northWall = convertView.findViewById(R.id.northWall);
		northWall.setVisibility((walls[0]) ? View.VISIBLE : View.INVISIBLE);
		View eastWall = convertView.findViewById(R.id.eastWall);
		eastWall.setVisibility((walls[1]) ? View.VISIBLE : View.INVISIBLE);
		View southWall = convertView.findViewById(R.id.southWall);
		southWall.setVisibility((walls[2]) ? View.VISIBLE : View.INVISIBLE);
		View westWall = convertView.findViewById(R.id.westWall);
		westWall.setVisibility((walls[3]) ? View.VISIBLE : View.INVISIBLE);
		//convertView = (SquareView)convertView;
		
		return convertView;	
	}
}
