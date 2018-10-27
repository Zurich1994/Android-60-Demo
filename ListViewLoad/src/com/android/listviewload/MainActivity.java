package com.android.listviewload;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
	 private ListView m_listView;   
	 private ArrayList<String> m_data = new ArrayList<String>();   
	 private ArrayAdapter<String> m_adapter; 
	 private int m_nLastItem = 0;   
	 
	 Handler mHandler=new Handler(){
		public void handleMessage(Message msg) {

			for(int i=m_listView.getCount();i<m_listView.getCount()+6;++i){
				m_data.add("我的项目："+i);
			}
			m_adapter.notifyDataSetChanged();
			m_listView.setSelection(m_nLastItem-1);
		}; 
	 };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_listView=(ListView) findViewById(R.id.listView);
        for(int i=0;i<6;++i){
        	m_data.add("我的项目："+i);
        }
        
        m_adapter=new ArrayAdapter<String>(this, R.layout.item, R.id.text, m_data);
        m_listView.setAdapter(m_adapter);
        
        m_listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				m_nLastItem=firstVisibleItem+visibleItemCount;
				if(m_nLastItem==totalItemCount){
					Toast.makeText(MainActivity.this, ""+m_nLastItem, Toast.LENGTH_LONG).show();
					
				}
			}
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
				if(m_nLastItem==m_adapter.getCount()&&scrollState==OnScrollListener.SCROLL_STATE_IDLE){
					mHandler.sendEmptyMessage(0); 
				}
			}
			
		});
    }
   
}