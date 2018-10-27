package com.android.choose;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotoAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> list;

	public PhotoAdapter(Context context, List<Map<String, Object>> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout view = null;

		if (convertView == null) {
			view = new LinearLayout(context);
			LayoutInflater inflater = LayoutInflater.from(context);
			inflater.inflate(R.layout.photo, view);
			ImageView imageview=(ImageView) view.findViewById(R.id.image);
			ImageView imageviewss=(ImageView) view.findViewById(R.id.imagess);
			ImageView imageviewsss=(ImageView) view.findViewById(R.id.imagesss);
			for(int i=0;i<list.size();i+=3){
				imageview.setImageBitmap((Bitmap) list.get(i).get("photo"));
				if((i+1)<list.size()){
					imageviewss.setImageBitmap((Bitmap) list.get(i+1).get("photo"));
				}
				if((i+2)<list.size()){
					imageviewsss.setImageBitmap((Bitmap) list.get(i+2).get("photo"));
				}
				
				
			}
			
			
		} else {
			view = (LinearLayout) convertView;
		}

		return view;
	}

	
}
