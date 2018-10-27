package com.fit.myview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	MyView myView;
	MyTheard myTheard;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���ó�����ʾӦ�ó�����ʾ������
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        myView=new MyView(this);
        setContentView(myView);
        myTheard=new MyTheard(myView);
        myTheard.start();
    }
} 