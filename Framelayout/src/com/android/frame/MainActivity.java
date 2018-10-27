package com.android.frame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView text=(TextView) findViewById(R.id.text);
        text.getBackground().setAlpha(30);
        
        Button btn=(Button) findViewById(R.id.btn);
        btn.getBackground().setAlpha(100);
    }
}