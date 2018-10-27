package com.android.openfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button open=(Button) findViewById(R.id.open);
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this, FileActivity.class); 
				startActivityForResult(intent, 1);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==1){
			if(data!=null){
				String path=data.getStringExtra("fileName");
				Toast.makeText(MainActivity.this,path,Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(MainActivity.this,"无法打开此文件！",Toast.LENGTH_LONG).show();
			}
			
		}
	}
}
