package com.example.test13_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class PieChart extends View {

	private Paint mPaint;
	private static final String mString = "Hello world my custom view!";

	public PieChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		mPaint = new Paint();

		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Style.FILL);
	}

	public PieChart(Context context, AttributeSet attr) {
		super(context, attr);
		// TODO Auto-generated constructor stub

		mPaint = new Paint();

		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Style.FILL);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		canvas.drawRect(new Rect(10, 0, 300, 500), mPaint);

		mPaint.setColor(Color.RED);
		canvas.drawText(mString, 50, 550, mPaint);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int PhotoFrameHeight = 100;
		int PhotoFrameWidth = 200;
		setMeasuredDimension(PhotoFrameWidth, PhotoFrameHeight);
	}

}