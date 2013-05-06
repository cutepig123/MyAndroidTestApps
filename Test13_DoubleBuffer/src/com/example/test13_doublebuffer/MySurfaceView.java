package com.example.test13_doublebuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
 
public class MySurfaceView extends SurfaceView implements Runnable, Callback {
    private SurfaceHolder mHolder; // ���ڿ���SurfaceView
    private Thread t; // ����һ���߳�
    private volatile boolean flag; // �߳����еı�ʶ�����ڿ����߳�
    private Canvas mCanvas; // ����һ�Ż���
    private Paint p; // ����һ֧����
    float m_circle_r = 10;
 
    public MySurfaceView(Context context) {
        super(context);
 
        mHolder = getHolder(); // ���SurfaceHolder����
        mHolder.addCallback(this); // ΪSurfaceView���״̬����
        p = new Paint(); // ����һ�����ʶ���
        p.setColor(Color.WHITE); // ���û��ʵ���ɫΪ��ɫ
        setFocusable(true); // ���ý���
    }
 
    /**
     * ��SurfaceView������ʱ�򣬵��ô˺���
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t = new Thread(this); // ����һ���̶߳���
        flag = true; // ���߳����еı�ʶ���ó�true
        t.start(); // �����߳�
    }
 
    /**
     * ��SurfaceView����ͼ�����ı��ʱ�򣬵��ô˺���
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }
 
    /**
     * ��SurfaceView���ٵ�ʱ�򣬵��ô˺���
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false; // ���߳����еı�ʶ���ó�false
        mHolder.removeCallback(this);
    }
 
    /**
     * ����Ļ������ʱ����
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
 
        return true;
    }
 
    /**
     * ���û�����ʱ����
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
        }
        return super.onKeyDown(keyCode, event);
    }
 
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        surfaceDestroyed(mHolder);
        return super.onKeyDown(keyCode, event);
    }
 
    @Override
    public void run() {
        while (flag) {
            //try {
            //    synchronized (mHolder) {
                    //Thread.sleep(100); // ���߳���Ϣ100����
                    Draw(); // �����Զ��廭������
            //    }
//            } 
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                if (mCanvas != null) {
//                    // mHolder.unlockCanvasAndPost(mCanvas);//����������ͼ�����ύ�ı䡣
// 
//                }
//            }
        }
    }
 
    /**
     * �Զ���һ���������ڻ����ϻ�һ��Բ
     */
    protected void Draw() {
        mCanvas = mHolder.lockCanvas(); // ��û������󣬿�ʼ�Ի�������
        if (mCanvas != null) {
        	mCanvas.drawRGB(255, 255, 255);
        	
        	//if(true)
            {
        		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            
	            paint.setColor(Color.BLUE);
	            paint.setStrokeWidth(10);
	            paint.setStyle(Style.FILL);
	            if (m_circle_r >= (getWidth() / 10)) {
	                m_circle_r = 0;
	            } else {
	                m_circle_r++;
	            }
	            //Log.d("JSHe", String.valueOf(m_circle_r));
	            
	            Bitmap pic = ((BitmapDrawable) getResources().getDrawable(
	                    R.drawable.ic_launcher)).getBitmap();
	            mCanvas.drawBitmap(pic, 0, 50, paint);
	            mCanvas.drawText("Double buffer", 0, 50, paint);
	            for (int i = 0; i < 5; i++)
	                for (int j = 0; j < 8; j++)
	                    mCanvas.drawCircle(
	                            (getWidth() / 5) * i + (getWidth() / 10),
	                            (getHeight() / 8) * j + (getHeight() / 16),
	                            m_circle_r, paint);
            }
            mHolder.unlockCanvasAndPost(mCanvas); // ��ɻ������ѻ�����ʾ����Ļ��
        }
    }
}