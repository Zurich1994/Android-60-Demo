package com.android.choose;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoosePhoto extends Activity {
    /** Called when the activity is first created. */
	List<Map<String,Object>> photosList=null;
	FileList fileList=new FileList(); 
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.choosephoto);
	        
	        
	        String path=Environment.getDataDirectory()+"/data/com.android.choose/files";
//	        String path="/sdcard";
	        Log.e("path", path);
	        photosList=fileList.fileList(path);
	        Log.e("size", ""+photosList.size());
//	        
//	    	MyAdapter adapter = new MyAdapter(MainActivity.this, photosList);
//	    	setListAdapter(adapter);
	        GridView grid=(GridView) findViewById(R.id.grid);
	        grid.setAdapter(new GridViewAdapter(this, photosList));
	  }
	  
	  class GridViewAdapter extends BaseAdapter{
		  Context mContext;
			private List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
			
			
			
			public GridViewAdapter(Context mContext, List<Map<String, Object>> list) {
				super();
				this.mContext = mContext;
				this.list = list;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return list.get(position);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ImageView imageView;
				if(convertView==null){
					 imageView = new ImageView(mContext); 
					 imageView.setLayoutParams(new GridView.LayoutParams(110, 130));
					 imageView.setAdjustViewBounds(false); 
					 imageView.setScaleType(ImageView.ScaleType.FIT_XY); 
					 imageView.setPadding(8, 10, 8, 10); 
				}else{
					 imageView = (ImageView) convertView; 
				}
				imageView.setImageBitmap((Bitmap) list.get(position).get("photo")); 
				imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String path=list.get(position).get("path").toString();
						Toast.makeText(mContext, path,Toast.LENGTH_LONG).show();
						finish();
					}
				});
				return imageView;
			}

	  }
}