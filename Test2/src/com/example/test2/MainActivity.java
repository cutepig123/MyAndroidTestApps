package com.example.test2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	int n;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button ibutton = (Button)findViewById(R.id.button1);
	     
		ibutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				n++;
				ibutton.setText("You clicked me " + n + " Times!");
				Toast.makeText(getApplicationContext(), "You clicked me " + n + " Times!", 1000).show();
				
//				Button b;
//				b=null;
//				b.setText("");
//				new AlertDialog.Builder(getApplicationContext())
//                .setTitle("Paracettamol")
//                .setMessage(
//                        "This medicine is generally used to cure Fever")
//                .setNeutralButton("OK", null).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
