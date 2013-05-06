package com.example.test5;

import android.app.Activity; 
import android.content.Context; 
import android.hardware.Sensor; 
import android.hardware.SensorEvent; 
import android.hardware.SensorEventListener; 
import android.hardware.SensorListener;
import android.hardware.SensorManager; 
import android.os.Bundle; 
import android.widget.TextView; 
 
public class SensorTest2 extends Activity implements SensorListener { 
    TextView textView; 
    StringBuilder []builders = new StringBuilder[2];
 
    @Override
	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(int sensor, float[] values) {
		// TODO Auto-generated method stub
		if( sensor==SensorManager.SENSOR_ACCELEROMETER)
		{
			StringBuilder builder =builders[0];
			builder.setLength(0); 
	        builder.append("SENSOR_ACCELEROMETER:");
			builder.append("x: "); 
	        builder.append(values[0]); 
	        builder.append(", y: "); 
	        builder.append(values[1]); 
	        builder.append(", z: "); 
	        builder.append(values[2]);
	        builder.append("\n"); 
		}
		if( sensor==SensorManager.SENSOR_ORIENTATION)
		{
			StringBuilder builder =builders[1];
			builder.setLength(0); 
	        builder.append("SENSOR_ORIENTATION:");
			builder.append("x: "); 
	        builder.append(values[0]); 
	        builder.append(", y: "); 
	        builder.append(values[1]); 
	        builder.append(", z: "); 
	        builder.append(values[2]); 
	        builder.append("\n");
		}
        textView.setText(builders[0].toString() + builders[1].toString()); 
	}

	@SuppressWarnings("deprecation")
	@Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        textView = new TextView(this); 
        setContentView(textView); 
 
        builders[0] =new StringBuilder();
        builders[1] =new StringBuilder();
        
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); 

//        if (!manager.registerListener(this,  SensorManager.SENSOR_ALL)) { 
//            textView.setText("Couldn't register accelerometer sensor listener"); 
//        } 
        
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) { 
            textView.setText("No accelerometer installed"); 
        } else { 
            if (!manager.registerListener(this,  SensorManager.SENSOR_ACCELEROMETER)) { 
                textView.setText("Couldn't register accelerometer sensor listener"); 
            } 
        }
        
        if (manager.getSensorList(Sensor.TYPE_ORIENTATION).size() == 0) { 
            textView.setText("No orientation installed"); 
        } else { 
            if (!manager.registerListener(this,  SensorManager.SENSOR_ORIENTATION)) { 
                textView.setText("Couldn't register orientation sensor listener"); 
            } 
        } 
    } 
    
}
