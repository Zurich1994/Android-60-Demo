package com.android.myanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private Button start;
	private ImageView img;
	private Spinner select;
	private Animation tanimation=null;
	
	private String str[]={ "平移动画", "透明度动画", "旋转动画", "缩放动画" };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        img=(ImageView) findViewById(R.id.img);
        select=(Spinner) findViewById(R.id.spinner);
        start=(Button) findViewById(R.id.start);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinneritem, str); 
        select.setAdapter(adapter);
        
        start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			initialAnimation();
			img.startAnimation(tanimation);
			}
		});
    }
    
    
    public void initialAnimation(){
    	switch(select.getSelectedItemPosition()){
    	case 0:
    		tanimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_PARENT, -0.5f,Animation.RELATIVE_TO_PARENT,-0.5f);
    		break;
    	case 1:
    		tanimation=new AlphaAnimation(0.1f, 0.1f);
    		break;
    	case 2:
    		tanimation = new RotateAnimation(0.0f, +720.0f); 
    		break;
    	case 3:
    		tanimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f); 
    		tanimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 200.0f, 0.0f); 
    		break;
    
    	}
    	//为动画设置完成所需时间 
    	tanimation.setDuration(2000); 
    }
}