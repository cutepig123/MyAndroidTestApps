package com.example.test5;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    String tests[] = { "LifeCycleTest", "SingleTouchTest", "MultiTouchTest", 
            "KeyTest", "AccelerometerTest", "SensorTest2", "AssetsTest", 
            "ExternalStorageTest", "SoundPoolTest", "MediaPlayerTest", 
            "FullScreenTest", "RenderViewTest", "ShapeTest", "BitmapTest", 
            "FontTest", "SurfaceViewTest" }; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
	     setListAdapter(new ArrayAdapter<String>(this, 
	                android.R.layout.simple_list_item_1, tests)); 
	}

	 @Override 
    protected void onListItemClick(ListView list, View view, int position, 
            long id) { 
        super.onListItemClick(list, view, position, id); 
        String testName = tests[position]; 
        try { 
            Class clazz = Class 
                    .forName("com.example.test5." + testName); 
            Intent intent = new Intent(this, clazz); 
            startActivity(intent); 
        } catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
        } 
    } 
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
