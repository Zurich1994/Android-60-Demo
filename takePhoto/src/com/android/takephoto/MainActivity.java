package com.android.takephoto;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity  {
	/** Called when the activity is first created. */

	private ImageView image;
	private Bitmap mBitmap = null; 

	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		image=(ImageView) findViewById(R.id.photo);
		Intent intent=new Intent(MainActivity.this, MainActivitytakephoto.class);
		startActivityForResult(intent,1);
	}	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			mBitmap=BitmapFactory.decodeFile(data.getExtras().getString("photopath"));
			image.setImageBitmap(mBitmap);
			}
		}

}