package com.kachaembarrassed.ui;
import com.kachaembarrassed.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
private ImageButton creBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		initEnvironment();
		initWindow();
		initLayoutsAndViews();
		
	}

	private void initEnvironment() {
		
	}
	private void initWindow(){
		setContentView(R.layout.activity_main);
		creBtn=(ImageButton)findViewById(R.id.imageButton2);
	}
	private void initLayoutsAndViews(){
		creBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent	intent = new Intent(MainActivity.this, MakingActivity.class);
				startActivity(intent);
				
			}
		});
		
	}
}
