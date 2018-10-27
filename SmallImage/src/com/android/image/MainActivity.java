package com.android.image;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        System.out.println(getImageString("/sdcard/driver.jpg"));
    }
  	public String getImageString(String imgFilePath){
  		Bitmap mBitmap=BitmapFactory.decodeFile(imgFilePath);
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f);
        Bitmap newBitmap=Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        
  		ByteArrayOutputStream out=new ByteArrayOutputStream();
  		newBitmap.compress(CompressFormat.PNG, 100, out);
  		byte []bytes=out.toByteArray();
  		String imageString=Base64.encodeToString(bytes, Base64.DEFAULT);
  		return imageString;
  	}
}