package com.fit.choose;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	FileList fileList=new FileList();
	List<Map<String,Object>> photosList=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        photosList=fileList.fileList(path);
    }
}