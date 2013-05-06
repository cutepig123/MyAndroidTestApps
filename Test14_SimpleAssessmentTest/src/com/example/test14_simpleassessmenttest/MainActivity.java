package com.example.test14_simpleassessmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	  LinearLayout layout;
	  TextView question;

	  @Override
	  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);

	    layout = new LinearLayout(this);
	    question = new TextView(this);

	    layout.setLayoutParams(new ViewGroup.LayoutParams(-1,-1));
	    layout.setBackgroundColor(getResources().getColor(android.R.color.black));

	    question.setLayoutParams(new ViewGroup.LayoutParams(-1,-2));
	    question.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
	    question.setTextSize(1,14);
	    question.setText("This is question1");
	    
	    layout.addView(question);

	    setContentView(layout);
	  }
}
