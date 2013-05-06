package com.example.test10_compass;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CompassView extends SurfaceView implements Runnable{

	Thread renderThread = null; 
    SurfaceHolder holder; 
    volatile boolean running = false; 
    
	private float bearing;
	Paint markerPaint, textPaint, circlePaint;
	String northString, eastString, southString, westString;
	int textHeight;
	String text;
	Random rand = new Random(); 
	
	public void setBearing(float bearing)
	{
		this.bearing =bearing;
	}
	public void setText(String text)
	{
		this.text =text;
	}
	
	void initView(){
		setFocusable(true);
		
		holder = getHolder();         
		Resources r =this.getResources();
		
		circlePaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(r.getColor(R.color.background_color));
		circlePaint.setStrokeWidth(2);
		circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		
		northString = r.getString(R.string.cardinal_north);
		southString = r.getString(R.string.cardinal_south);
		eastString = r.getString(R.string.cardinal_east);
		westString = r.getString(R.string.cardinal_west);
		
		textPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(r.getColor(R.color.text_color));
		
		markerPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		markerPaint.setColor(r.getColor(R.color.marker_color));
	}
	
	public void resume() {           
        running = true; 
        renderThread = new Thread(this); 
        renderThread.start();          
    }    
	
	 public void run() { 
         while(running) {   
             if(!holder.getSurface().isValid()) 
                 continue; 
              
             Canvas canvas = holder.lockCanvas();  
             //Log.d("JSHe", "run " + String.valueOf(canvas));
             MyDraw(canvas);                        
             holder.unlockCanvasAndPost(canvas);             
         } 
     } 
	 
	public CompassView(Context context) {
		super(context);
		initView();
	}
	
	public CompassView(Context context, AttributeSet atrs) {
		super(context);
		initView();
	}

	public CompassView(Context context, AttributeSet atrs, int defaultStyle) {
		super(context);

		initView();
	}
	
//	@Override
//	protected void onDraw(Canvas canvas) {
//		Log.d("JSHe", "onDraw " + String.valueOf(canvas));
//		super.onDraw(canvas);
//	}
	
	protected void MyDraw(Canvas canvas) {
		
		//Log.d("JSHe", "bearing " + bearing);
		
		int px =getMeasuredWidth()/2;
		int py =getMeasuredHeight()/2;
		int radius =Math.min(px, py);
		
		canvas.drawRGB(0,0,0);
		canvas.drawCircle(px, py, radius, circlePaint);
		canvas.save();
		canvas.rotate(-bearing, px, py);
		
		int textWidth =(int)textPaint.measureText("W");
		int cardinalX =px -textWidth/2;
		//int textMargin =10;
		int cardinalY =py -radius +textHeight;
		
		if(null!= text)
			canvas.drawText(text, px, py, textPaint);
			
		for(int i=0;i<24;i++){
			canvas.drawLine(px, py-radius, px, py-radius+textHeight, markerPaint);
			
			canvas.save();
			canvas.translate(0, textHeight);
			
			if(i%6==0){
				String dirString ="";
				switch(i){
					case 0:{
						dirString =northString;
						int arrowY =2*textHeight;
						canvas.drawLine(px, arrowY, px-5, 3*textHeight, markerPaint);
						canvas.drawLine(px, arrowY, px+5, 3*textHeight, markerPaint);
						break;
					}
					case 6:
						dirString =eastString; break;
					case 12:
						dirString =southString; break;
					case 18:
						dirString =westString; break;
				}
				canvas.drawText(dirString, cardinalX, (float) (cardinalY), textPaint);		
			}
			else if(i%3==0)
			{
				String angle =String.valueOf(i*15);
				float angleTextWidth =textPaint.measureText(angle);
				int angleTextX =(int)(px -angleTextWidth/2);
				int angleTextY =py -radius +textHeight;
				canvas.drawText(angle, angleTextX, angleTextY, textPaint);
			}
			
			canvas.restore();
			canvas.rotate(15,px,py);
				
		}
		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int w =measure(widthMeasureSpec);
		int h =measure(heightMeasureSpec);
		
		int d =Math.min(w, h);
		setMeasuredDimension(d, d);
		
		float textSz =textPaint.getTextSize();
		textPaint.setTextSize((float) (getMeasuredWidth()/15.0));
		textHeight =(int) textPaint.measureText("Y");
	}

	private int measure(int measureSpec){
		int result =0;
		
		int specMode =MeasureSpec.getMode(measureSpec);
		int specSize =MeasureSpec.getSize(measureSpec);
		
		if( specMode == MeasureSpec.UNSPECIFIED)
			result =200;
		else
			result =specSize;
		return result;
	}
	
	public void pause() {                         
        running = false;                         
        while(true) { 
            try { 
                renderThread.join(); 
            } catch (InterruptedException e) { 
                // retry 
            } 
        }       
    }      
}
