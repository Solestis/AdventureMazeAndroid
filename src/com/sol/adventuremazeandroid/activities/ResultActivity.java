package com.sol.adventuremazeandroid.activities;

import com.sol.adventuremazeandroid.R;
import com.sol.adventuremazeandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_result);
		
		Intent intent = getIntent();
		int steps = intent.getIntExtra("steps", 0);
		
		TextView stepsText = (TextView) findViewById(R.id.stepTextView);
		stepsText.setText("Exit found in: " + steps + " steps.");
	}
	
	public void navMainMenu(View view) {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void navGame(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
}
