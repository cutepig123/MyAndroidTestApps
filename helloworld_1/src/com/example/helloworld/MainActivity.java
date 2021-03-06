package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.view.View;;

public class MainActivity extends Activity 
implements View.OnClickListener{
	Button button;
	int touchCnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        button =new Button(this);
        button.setText("Touch me!");
        button.setOnClickListener(this);
        
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	touchCnt++;
            	button.setText("(2)Touch me " + touchCnt + " Times!");
            }
        });
        
        setContentView(button);
    }
    
    @Override
    public void onClick(View v) {
    	touchCnt++;
    	button.setText("(1)Touch me " + touchCnt + " Times!");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
