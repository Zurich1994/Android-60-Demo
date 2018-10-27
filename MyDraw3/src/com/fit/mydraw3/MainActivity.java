package com.fit.mydraw3;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Animation myAnimation=AnimationUtils.loadAnimation(this, R.anim.myanim);
        ImageView myImage=(ImageView) findViewById(R.id.myImage);
        myImage.startAnimation(myAnimation);
    }
}    