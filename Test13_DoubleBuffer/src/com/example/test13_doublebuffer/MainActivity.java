package com.example.test13_doublebuffer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	 MyView mv;
	 MySurfaceView mv2;
	 
    float m_circle_r = 10;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        if(false)
        {
        	mv = new MyView(this);
        	setContentView(mv);
        	
        	Timer timer = new Timer();  
            timer.scheduleAtFixedRate(new MyTask(), 1, 1);  
        }
        else
        {
        	mv2 = new MySurfaceView(this);
        	setContentView(mv2);
        }
        
        
    }
    
    public class MyView extends View {
        MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setStyle(Style.FILL);
            if (m_circle_r >= (getWidth()/10))
            {
                m_circle_r = 0;
            }
            else
            {
                m_circle_r++;
            }
            Bitmap pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
            canvas.drawBitmap(pic, 0, 0, paint);
            canvas.drawText("No double buffer", 0, 50, paint);
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 8; j++)
                	canvas.drawCircle((getWidth()/5)*i+(getWidth()/10), (getHeight()/8)*j+(getHeight()/16), m_circle_r, paint);
        }

    }
    private class MyTask extends TimerTask{  
        @Override  
        public void run() {  
            mv.postInvalidate();
        }     
    }  

}
