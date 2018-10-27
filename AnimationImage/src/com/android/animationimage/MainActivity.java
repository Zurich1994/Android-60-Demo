package com.android.animationimage;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private ImageView mImage;
	private int index=0;
	private Animation mAlphaAnimation;
	private Animation mScaleAnimation;
	private Animation mTranslateAnimation;
	private Animation mRotateAnimation;
	private int []images={R.drawable.a,R.drawable.c,R.drawable.e,R.drawable.f};
	Timer mTimer=new Timer();
	Handler mHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				if(index<images.length){
					mImage.setImageResource(images[index]);
					switch(index){
					case 0:
						mScaleAnimation=new ScaleAnimation(0.0f, 1.5f, 
								0.0f, 1.5f, Animation .RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.0f);
						mScaleAnimation.setDuration(2000);
						mImage.setAnimation(mScaleAnimation);
						break;
					case 1:
						mRotateAnimation=new RotateAnimation(0.0f, 360.0f,   
								Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
						mRotateAnimation.setDuration(2000);
						mImage.setAnimation(mRotateAnimation);
						break;
					case 2:
						mAlphaAnimation = new AlphaAnimation(0.1f, 1.0f);   
						mAlphaAnimation.setDuration(2000);  
						mImage.setAnimation(mAlphaAnimation);
						break;
					case 3:
						mTranslateAnimation = new TranslateAnimation(0, 100, 0, 100); 
						mTranslateAnimation.setDuration(2000); 
						mImage.setAnimation(mTranslateAnimation);
						break;
					}
					
					index++;
				}else{
					index=0;
				}
				
				break;
			}
		};
	}; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mImage=(ImageView) findViewById(R.id.mimage);
        mTimer.schedule(task,new Date(), 3000);
    }
    
    TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=new Message();
			msg.what=1;
			mHandler.sendMessage(msg);
		}
	};
}