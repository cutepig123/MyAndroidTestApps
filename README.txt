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
