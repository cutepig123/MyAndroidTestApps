package com.example.test10_compass;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CompassView extends View{

	private float bearing;
	
	Paint markerPaint, textPaint, circlePaint;
	String northString, eastString, southString, westString;
	int textHeight;
	Random rand = new Random(); 
	
//	public void setBearing(float bearing)
//	{
//		this.bearing =bearing;
//	}
	void initView(){
		setFocusable(true);
		
//		setBearing(2);
		
		Resources r =this.getResources();
		
		circlePaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(r.getColor(R.color.background_color));
		circlePaint.setStrokeWidth(1);
		circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		
		northString = r.getString(R.string.cardinal_north);
		southString = r.getString(R.string.cardinal_south);
		eastString = r.getString(R.string.cardinal_east);
		westString = r.getString(R.string.cardinal_west);
		
		textPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(r.getColor(R.color.text_color));
		
		textHeight =(int) textPaint.measureText("yY");
		
		markerPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		markerPaint.setColor(r.getColor(R.color.marker_color));
	}
	
	public CompassView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public CompassView(Context context, AttributeSet atrs) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	public CompassView(Context context, AttributeSet atrs, int defaultStyle) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		bearing =(float) (rand.nextFloat()*360);
		Log.d("JSHe", "bearing " + bearing);
		
		// TODO Auto-generated method stub
		int px =getMeasuredWidth()/2;
		int py =getMeasuredHeight()/2;
		int radius =Math.min(px, py);
		
		canvas.drawCircle(px, py, radius, circlePaint);
		canvas.save();
		canvas.rotate(-bearing, px, py);
		
		int textWidth =(int)textPaint.measureText("W");
		int cardinalX =px -textWidth/2;
		int cardinalY =py -radius +textHeight;
		
		for(int i=0;i<24;i++){
			canvas.drawLine(px, py-radius, px, py-radius+10, markerPaint);
			
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
				canvas.drawText(dirString, cardinalX, cardinalY, textPaint);		
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
		//super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int w =measure(widthMeasureSpec);
		int h =measure(heightMeasureSpec);
		
		int d =Math.min(w, h);
		setMeasuredDimension(d, d);
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
}
