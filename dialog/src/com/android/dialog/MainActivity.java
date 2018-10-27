package com.android.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Dialog dialog=new Dialog(this);
        dialog.setTitle("dgdgdjkgkhsfgdfhfhgf");
        dialog.setContentView(R.layout.dialog);
        dialog.show();
    }
}