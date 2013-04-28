package cutepig.example.test3_todolist;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	public String[] mFoods = { "Food1", "Food2", "Food3" };
	public String[] mPlaces = { "Place1", "Place2", "Place3" };
	public String[] mRatings = { "1 Star", "2 Star", "3 Star" };
	
	public class MyCustomAdapter extends ArrayAdapter<String> {

		public MyCustomAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(android.R.layout.simple_list_item_2,
					parent, false);

			TextView tv1 = (TextView) row.findViewById(android.R.id.text1);
			tv1.setText(mFoods[position]);
			TextView tv2 = (TextView) row.findViewById(android.R.id.text2);
			tv2.setText(mPlaces[position]);

//			 TextView tv3 = (TextView) row.findViewById(R.id.textView3);
//			 tv3.setText(mRatings[position]);
			 //ImageView iv1=(ImageView) row.findViewById(R.id.imageView1);
			 //iv1.setImageResource(mPics[position]);
			 
			return row;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		setListAdapter(new MyCustomAdapter(this,android.R.layout.simple_list_item_2, mFoods));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
