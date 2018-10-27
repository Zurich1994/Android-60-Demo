package com.fit.flipperimage;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener {
    /** Called when the activity is first created. */
	private int []images={R.drawable.a,R.drawable.c,R.drawable.e,R.drawable.f};
	private GestureDetector detector;
	private int index=0;
	private int flag=0;
	private ViewFlipper flipper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        flipper=(ViewFlipper) findViewById(R.id.imageFlipper);
        for(int i=0;i<images.length;i++){
        	ImageView image=new ImageView(this);
        	image.setImageResource(images[i]);
        	flipper.addView(image);
        	index++;
        }
        detector=new GestureDetector(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	return this.detector.onTouchEvent(event);
    }
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if(e1.getX()-e2.getX()>10){
			flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
			flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
			if(flag%4==0){
				this.flipper.showPrevious();
			}else{
				this.flipper.showNext();
			}
			flag=(flag+1)%4;
		}else if(e1.getX()-e2.getX()<10){
			flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
			flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
			if(flag%4==0){
				flipper.showNext();
			}else{
				flipper.showPrevious();
			}
			flag=(flag+1)%4;
		}
		return true;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}