package com.android.pull;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn=(Button) findViewById(R.id.btn);
        
        Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.xiaoxiong);
        
        /* …Ë÷√Õº∆¨µƒøÌ∏ﬂ */
        Matrix matrix = new Matrix();
        int mWidth=bit.getWidth();
        int mHeight=bit.getHeight();
        float scaleWidth=(float)200/mWidth;
        float scaleHeight=(float)200/mHeight;
        Log.i("scale", scaleWidth+"++++++++++++"+scaleHeight);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap=Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);
        
        final ImageView myimage=(ImageView) findViewById(R.id.image);
        myimage.setImageBitmap(newBitmap);
        btn.setOnTouchListener(new OnTouchListener() {
        	int []temp=new int[]{0,0};
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int eventAction=event.getAction();
				int x=(int) event.getRawX();
				int y=(int) event.getRawY();
				switch(eventAction){
				case MotionEvent.ACTION_DOWN:
					temp[0]=(int) event.getX();
					temp[1]=y-v.getTop();
					break;
				case MotionEvent.ACTION_MOVE:
					v.layout(x-temp[0], y-temp[1], x+v.getWidth()-temp[0], y-temp[1]+v.getHeight());
					v.postInvalidate();
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				return false;
			}
		});
        
        myimage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 int mx;
				int my;              
				                 switch(event.getAction()) {                  
				                case MotionEvent.ACTION_MOVE:     
				                     mx = (int)(event.getRawX());     
				                     my = (int)(event.getRawY() - 50);     
				                          
				                     v.layout(mx - myimage.getWidth()/2, my - myimage.getHeight()/2, mx + myimage.getWidth()/2, my + myimage.getHeight()/2);     
				                     break;     
				                 }     
				                return true;     
				             }});     

    }
}