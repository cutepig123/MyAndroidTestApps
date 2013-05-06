package com.example.test10_compass;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends Activity {

	CompassView compview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		compview =new CompassView(this);
		setContentView(compview);
		

		SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); 

		if (manager.getSensorList(Sensor.TYPE_ORIENTATION).size() == 0) { 
			compview.setText("No orientation installed"); 
        } else { 
        	if (!manager.registerListener(new SensorListener() {
				
				@Override
				public void onSensorChanged(int sensor, float[] values) {
					//compview.setText(String.valueOf(values[0]));
					compview.setBearing(values[0]);
					compview.invalidate();
				}
				
				@Override
				public void onAccuracyChanged(int sensor, int accuracy) {
					
				}
			},  SensorManager.SENSOR_ORIENTATION, SensorManager.SENSOR_DELAY_FASTEST)) { 
            	compview.setText("Couldn't register orientation sensor listener"); 
            } 
        }
		
		compview.invalidate();
	}

	protected void onResume() { 
        super.onResume(); 
        compview.resume(); 
    } 
	
	protected void onPause() { 
        super.onPause();          
        compview.pause(); 
    }     
}
