package com.fit.slidingdriver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	SlidingDrawer myDrawer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myDrawer=(SlidingDrawer) findViewById(R.id.sliding);
        myDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
					
			}
		});
        myDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				Toast.makeText(MainActivity.this, "¹Ø±Õ",Toast.LENGTH_LONG).show();
			}
		});
    }
}