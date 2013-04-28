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

- SMSControl
Requirements
	利用SMS控制手机
	支持命令： Toast, Enable/Disa/GetGPS, Enable/DisaInternet, GetIP, GetLocation, SendSMS, 拍照， screenShot, LockScreen, Send通信簿，SendAllSMSData, 设置/关闭闹铃
Proposal:
	程序后台运行
	设置界面，
		可以让用户选择支持的SMS命令，
		密码设置，
		选择是否向发命令方Send Reply
		Log all control message with the time
	收到SMS命令后，start another activity and prompt user in 30s. user can select YES or NO. 

- test8_intentservice
reference： http://www.vogella.com/articles/AndroidServices/article.html#services_declare
学习创建，使用service
学习intent：
	setData --> getData
	putExtra --> getStringExtra, getExtras().get
学习Messager, Message
	new Messenger(handler)
	send

	Message.obtain()
		arg1
		obj
学习用文件读写api下载文件

程序执行流程

lauch MainActivity ->a handler is created --> user  click button --> intent, and message created, and service started --> sercice onHandleIntent is called -->

Q： add an sdcard to the emulator
During the setup for the virtual device a window with options should come up that asks if you want an sdcard and what size you want it. This is how i did it


- Test9_LocalService
reference： http://www.vogella.com/articles/AndroidServices/article.html#services_declare
need testing。。。

- Test10_compass
定制一个指南针的视图
有个问题不知怎样解决： 我想加入一个progress bar来控制方向，但发现progress bar显示不出来

- Test11_TodoList
定制了View的TodoList

- Test12_TodoList
通过MyCustomAdapter实现custom listview
参考http://blog.joomla.org.tw/android/180-ListView%E4%B9%8B%E4%BA%8C%EF%BC%9A%E6%94%B9%E5%AF%ABArrayAdapter%E7%9A%84getView.html

