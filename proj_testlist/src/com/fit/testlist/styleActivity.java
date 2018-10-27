package com.fit.testlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class styleActivity extends ListActivity {
@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.styleactivity);
	
	ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
	
	Map<String, String> map1=new HashMap<String, String>();
	Map<String, String> map2=new HashMap<String, String>();
	map1.put("aa","aaaaaaaaaaaaaaaa");
	map1.put("bb","bbbbbbbbbbbbbbbbbb");
	map2.put("cc","cccccccccccccccccc");
	map2.put("dd","dddddddddddddddddd");
	list.add(map1);
	list.add(map2);
	
	
	String  style=getIntent().getExtras().getString("style");
	if(style.equals("Service,address,city")){
		SimpleAdapter adapter=new SimpleAdapter(styleActivity.this,list,R.layout.listhori,new String[]{"aa","bb"},new int[]{R.id.view1,R.id.view2});
		setListAdapter(adapter);
	}else{
		SimpleAdapter adapter=new SimpleAdapter(styleActivity.this,list,R.layout.listvertical,new String[]{"cc","dd"},new int[]{R.id.view1,R.id.view2});
		setListAdapter(adapter);
	}
}
}
