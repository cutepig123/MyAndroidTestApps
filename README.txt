android学习过程中写的一些小程序

- helloworld_1
利用代码生成按钮，
按钮单击响应

-Test2
使用资源
Button ibutton = (Button)findViewById(R.id.button1);
使用toast

-Test3_TodoList
绑定listview与arraylist
响应edittext回车事件
通过修改资源添加Admob广告

-Test4_ADs
通过代码添加广告

Q：ID是什么？

Q：04-21 06:27:05.064: E/Ads(742): Not enough space to show ad! Wants: <480, 75>, Has: <432, 724>

http://stackoverflow.com/questions/13491560/admob-not-enough-space-to-show-ad-error
It means there is no space for to display ad in your layout. 
修改主layout的padding为0，android:layout_width="fill_parent"

-Test5
使用ListActivity
使用Class
使用Intent
使用StringBuilder, TextView
Activity的生命周期
触摸屏响应OnTouch(v, MotionEvent)
键盘响应onkeylistener
加速度计accelerometer
文件读写, Assets, 外部文件
不同种类的音量控制，SoundPool，context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
MediaPlayer
WakeLock，全屏，View,画线，园，Canvas, Paint
BitmapFactory, Bitmap, Canvas.DrawBitmap
Font
SurfaceHolder -> Canvas
Thread, Runable


- Test6_SMS
测试接收，发送SMS
在receive里面分析传入的数据，执行SendSMS,Toast预定义动作
参考 http://mobiforge.com/developing/story/sms-messaging-android 

可以开两个模拟器做短信收发测试
