androidѧϰ������д��һЩС����

- helloworld_1
���ô������ɰ�ť��
��ť������Ӧ

-Test2
ʹ����Դ
Button ibutton = (Button)findViewById(R.id.button1);
ʹ��toast

-Test3_TodoList
��listview��arraylist
��Ӧedittext�س��¼�
ͨ���޸���Դ���Admob���

-Test4_ADs
ͨ��������ӹ��

Q��ID��ʲô��

Q��04-21 06:27:05.064: E/Ads(742): Not enough space to show ad! Wants: <480, 75>, Has: <432, 724>

http://stackoverflow.com/questions/13491560/admob-not-enough-space-to-show-ad-error
It means there is no space for to display ad in your layout. 
�޸���layout��paddingΪ0��android:layout_width="fill_parent"

-Test5
ʹ��ListActivity
ʹ��Class
ʹ��Intent
ʹ��StringBuilder, TextView
Activity����������
��������ӦOnTouch(v, MotionEvent)
������Ӧonkeylistener
���ٶȼ�accelerometer
�ļ���д, Assets, �ⲿ�ļ�
��ͬ������������ƣ�SoundPool��context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
MediaPlayer
WakeLock��ȫ����View,���ߣ�԰��Canvas, Paint
BitmapFactory, Bitmap, Canvas.DrawBitmap
Font
SurfaceHolder -> Canvas
Thread, Runable


- Test6_SMS
���Խ��գ�����SMS
��receive���������������ݣ�ִ��SendSMS,ToastԤ���嶯��
�ο� http://mobiforge.com/developing/story/sms-messaging-android 

���Կ�����ģ�����������շ�����

- SMSControl
Requirements
	����SMS�����ֻ�
	֧����� Toast, Enable/Disa/GetGPS, Enable/DisaInternet, GetIP, GetLocation, SendSMS, ���գ� screenShot, LockScreen, Sendͨ�Ų���SendAllSMSData, ����/�ر�����
Proposal:
	�����̨����
	���ý��棬
		�������û�ѡ��֧�ֵ�SMS���
		�������ã�
		ѡ���Ƿ������Send Reply
		Log all control message with the time
	�յ�SMS�����start another activity and prompt user in 30s. user can select YES or NO. 

- test8_intentservice
reference�� http://www.vogella.com/articles/AndroidServices/article.html#services_declare
ѧϰ������ʹ��service
ѧϰintent��
	setData --> getData
	putExtra --> getStringExtra, getExtras().get
ѧϰMessager, Message
	new Messenger(handler)
	send

	Message.obtain()
		arg1
		obj
ѧϰ���ļ���дapi�����ļ�

����ִ������

lauch MainActivity ->a handler is created --> user  click button --> intent, and message created, and service started --> sercice onHandleIntent is called -->

Q�� add an sdcard to the emulator
During the setup for the virtual device a window with options should come up that asks if you want an sdcard and what size you want it. This is how i did it


- Test9_LocalService
reference�� http://www.vogella.com/articles/AndroidServices/article.html#services_declare
need testing������

- Test10_compass
����һ��ָ�������ͼ
�и����ⲻ֪��������� �������һ��progress bar�����Ʒ��򣬵�����progress bar��ʾ������

- Test11_TodoList
������View��TodoList

- Test12_TodoList
ͨ��MyCustomAdapterʵ��custom listview
�ο�http://blog.joomla.org.tw/android/180-ListView%E4%B9%8B%E4%BA%8C%EF%BC%9A%E6%94%B9%E5%AF%ABArrayAdapter%E7%9A%84getView.html

