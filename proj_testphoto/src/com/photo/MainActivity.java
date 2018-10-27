package com.photo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	ImageView image;
	int i=0;
	int []photos=new int[]{R.drawable.a,R.drawable.b,R.drawable.c};
	
	Timer timer=new Timer();
    Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
			case 1:
				if(i<photos.length){
					image.setImageResource(photos[i]);
				}else{
					i=-1;
				}
				i++;
				break;
			}
		}
	};
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        image=(ImageView) findViewById(R.id.image);
        image.setImageResource(photos[0]);
        timer.schedule(task,new Date(),2000);
    }
    
    
    TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			Message message=new Message();
			message.what=1;
			handler.sendMessage(message);
		}
	}; 
}