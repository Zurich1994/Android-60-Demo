package com.android.ballmove;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class SportActivity extends Activity {
	 public int screenWidth ;   
	          public int screenHeight ;   
	          BallSurfaceView bsv ;   
	     /** Called when the activity is first created. */  
	     @Override  
	     public void onCreate(Bundle savedInstanceState) {   
	         super.onCreate(savedInstanceState);   
	         bsv = new BallSurfaceView(this);   
	        //�����Ļ�ߴ�   
	        DisplayMetrics dm = new DisplayMetrics();   
	     dm = this.getApplicationContext().getResources().getDisplayMetrics();   
	              screenWidth = dm.widthPixels;   
	               screenHeight = dm.heightPixels;   
	            //������Ϊ����ȫ��   
	         requestWindowFeature(Window.FEATURE_NO_TITLE);   
	                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,    
	                                 WindowManager.LayoutParams.FLAG_FULLSCREEN);   
	          
	       setContentView(bsv);   
	     }   

}