package com.sol.adventuremazeandroid.activities;

import com.sol.adventuremazeandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState, R.layout.activity_menu);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
	}
	
	public void startGame(View view) {
		System.out.println("Start the game!");
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
}
