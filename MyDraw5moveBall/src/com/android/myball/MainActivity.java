package com.android.myball;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���ò���ʾ����
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //����Ϊȫ��ģʽ
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        BallView bv=new BallView(this);
        setContentView(bv);
    }
}