package com.example.test10_compass;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	CompassView view;
	//ProgressBar bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		view =(CompassView)findViewById(R.id.compassView);
		//bar =(ProgressBar)findViewById(R.id.progressBar1);
		
		//bar.setMax(10);
		//bar.setOnClickListener(this);
	}

	//@Override
	// public void onClick (View v) {
	//	 int p =bar.getProgress();
//		 view.setBearing(p);
	// }
}
