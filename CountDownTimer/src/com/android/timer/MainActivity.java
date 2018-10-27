package com.android.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	TextView time;
	CountDownTimer mTime;
	Button start;
	int mtime=60;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        time=(TextView) findViewById(R.id.time);
        start=(Button) findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTime.start();
			}
		});
        mTime=new CountDownTimer(30000,1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				mtime=mtime-1;
				time.setText("时间："+mtime);
			}
			@Override
			public void onFinish() {
				time.setText("时间已到！");
			}
		};
    }
   
}