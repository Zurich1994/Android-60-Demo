package com.fit.background;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	String path;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button choose=(Button) findViewById(R.id.choose);
        choose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					Intent i=new Intent();
					i.setType("image/*");
					i.setAction(Intent.ACTION_GET_CONTENT);
					Intent intent=Intent.createChooser(i, "Select Picture");
					startActivityForResult(intent, 2);
			}
		});
//        	Bitmap bit=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/xiaoxiong.jpg");
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode){
    	case 2:
    		Uri imageUri = null;
    		if(data!=null){
    		imageUri=data.getData();
    	
    		
    		String imagePath="";
    		String[] projection={MediaStore.Images.Media.DATA};
    		Cursor cursor=managedQuery(imageUri, projection, null, null, null);
    		if(cursor!=null){
    			int colomn_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    			cursor.moveToFirst();
    			imagePath=cursor.getString(colomn_index);
    		}
    		 Bitmap bit=BitmapFactory.decodeFile(imagePath);	
    	        try {
    	        		WallpaperManager manager=WallpaperManager.getInstance(this);
    					manager.setBitmap(bit);
    					finish();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    				
    		}
    		Toast.makeText(MainActivity.this, path, Toast.LENGTH_LONG).show();
    		break;
    	}
    }
}