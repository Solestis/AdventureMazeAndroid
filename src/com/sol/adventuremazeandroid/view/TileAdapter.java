package com.sol.adventuremazeandroid.view;

import java.util.List;
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
		
		
		if(convertView == null && tile.getView() == null) {
			convertView = LayoutInflater.from(getContext()).inflate(tile.layoutID, parent, false);	
			tile.setView(convertView);
		} else {
			convertView = tile.getView();
		}
		tile.updateView();
		
		return convertView;	
	}
}
