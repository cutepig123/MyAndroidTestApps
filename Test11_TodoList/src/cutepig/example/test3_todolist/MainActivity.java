package cutepig.example.test3_todolist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
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

	// private AdView adView;
	ArrayList<String> al;

	private String getConfigFile()
	{
		ContextWrapper cw = new ContextWrapper(this);
		File directory = cw.getDir("config", Context.MODE_PRIVATE);
		return directory.toString() + "config.txt";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText et = (EditText) findViewById(R.id.editText1);
		Button btn = (Button) findViewById(R.id.button1);
		ListView lv = (ListView) findViewById(R.id.listView1);
		al = new ArrayList<String>();
		
		File file = new File(getConfigFile());
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
	        String line; 
	        while ((line = reader.readLine()) != null) { 
	            al.add(line);
	        } 
	        reader.close(); 
		} catch (IOException e) {
			Log.d("XX","Open file fails");
		}
        
		int resID =R.layout.todolist_item;
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				resID, al);

		lv.setAdapter(aa);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String s = et.getText().toString();
				aa.insert(s, 0);
				aa.notifyDataSetChanged();
				et.setText("");
			}
		});

		et.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_ENTER) {
					String s = et.getText().toString();
					aa.insert(s, 0);
					aa.notifyDataSetChanged();
					et.setText("");
					return true;
				}
				return false;
			}
		});

		// 建立 adView
		// adView = new AdView(this, AdSize.BANNER, "a151737708df4ce");
		//
		// // 查詢 LinearLayout (假設您已經提供)
		// // 屬性是 android:id="@+id/mainLayout"
		// LinearLayout layout = (LinearLayout)findViewById(R.id.myLayout);
		//
		// // 在其中加入 adView
		// layout.addView(adView);
		// adView.loadAd(new AdRequest());

		// // 啟用泛用請求，並隨廣告一起載入
		AdView ad = (AdView) findViewById(R.id.adView);
		ad.loadAd(new AdRequest());
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (isFinishing()) {
			File textFile = new File(getConfigFile());

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						textFile));

				for (int i = 0; i < al.size(); i++)
					writer.write(al.get(i) + "\n");

				writer.close();
			} catch (IOException e) {
				Log.d("XX","Write file fails");
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
