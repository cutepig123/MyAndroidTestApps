package cutepig.example.test10_compass;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import cutepig.example.test10_compass.R;
import com.google.ads.AdView;

public class MainActivity extends Activity {

	CompassView compview;
	AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
//		LinearLayout myLayout = (LinearLayout)findViewById(R.id.myLayout);
//		//myLayout.setBackgroundColor(0);
//	    
		compview =(CompassView)findViewById(R.id.myCompassView);
//		
//		adView = new AdView(this, AdSize.BANNER, "a151737708df4ce");
//		//adView.setLayoutParams(params)
//	    // 在其中加入 adView
//		myLayout.addView(adView);
//	    adView.loadAd(new AdRequest());

		{
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
		
		
	}

}
