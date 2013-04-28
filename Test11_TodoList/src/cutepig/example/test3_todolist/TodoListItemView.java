package cutepig.example.test3_todolist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TodoListItemView extends TextView{

	Paint marginPaint, linePaint;
	int paperColor;
	float margin;
	
	void init()
	{
		paperColor =0xaaffff99;
		int lineColor =0xff0000ff;
		//int textColor =0xaa0000ff;
		int marginColor =0x90ff0000;
		margin =30;
		
		marginPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		marginPaint.setColor(marginColor);
		
		linePaint =new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setColor(lineColor);
		
	}
	public TodoListItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public TodoListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public TodoListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		canvas.drawColor(paperColor);
		canvas.drawLine(0, 0, getMeasuredWidth(), 0, linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
		
		canvas.save();
		canvas.translate(margin, 0);
		
		super.onDraw(canvas);
		canvas.restore();
	}

}
