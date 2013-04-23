package com.example.test3_todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends Activity {
	
//	private AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText et =(EditText)findViewById(R.id.editText1);
		Button btn =(Button)findViewById(R.id.button1);
		ListView lv =(ListView)findViewById(R.id.listView1);
		final ArrayList<String> al =new ArrayList<String>();
		final ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
		lv.setAdapter(aa);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String s=et.getText().toString();
				aa.insert(s, 0);
				aa.notifyDataSetChanged();
				et.setText("");
			}
		});
		
		et.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction()==KeyEvent.ACTION_DOWN &&
						keyCode==KeyEvent.KEYCODE_ENTER)
				{
					String s=et.getText().toString();
					aa.insert(s, 0);
					aa.notifyDataSetChanged();
					et.setText("");
					return true;
				}
				return false;
			}
		});
		
		 // 建立 adView
//	    adView = new AdView(this, AdSize.BANNER, "a151737708df4ce");
//
//	    // 查詢 LinearLayout (假設您已經提供)
//	    // 屬性是 android:id="@+id/mainLayout"
//	    LinearLayout layout = (LinearLayout)findViewById(R.id.myLayout);
//
//	    // 在其中加入 adView
//	    layout.addView(adView);
//	    adView.loadAd(new AdRequest());

//	    // 啟用泛用請求，並隨廣告一起載入
		AdView ad = (AdView) findViewById(R.id.adView);
	    ad.loadAd(new AdRequest());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	@Override
//	public void onConfigurationChanged(Configuration cfg)
//	{
//		super.onConfigurationChanged(cfg);
//	}
}
