package com.fit.listviewimage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private List<ImageAndText> list=new ArrayList<ImageAndText>();
	private ImageAndTextListAdapter  adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageAndText imageText1=new ImageAndText("http://www.wallcoo.com/nature/country_wildfield_landscape_1600x1200/wallpapers/1680x1050/country_field_landscape_photo_EA52056.jpg", "第一张");
        ImageAndText imageText2=new ImageAndText("http://www.wallcoo.com/nature/country_wildfield_landscape_1600x1200/wallpapers/1680x1050/country_field_landscape_photo_EA52065.jpg", "第二张");
        ImageAndText imageText3=new ImageAndText("http://www.wallcoo.com/nature/country_wildfield_landscape_1600x1200/wallpapers/1680x1050/country_field_landscape_photo_EA52059.jpg", "第一张");
        ImageAndText imageText4=new ImageAndText("http://www.wallcoo.com/nature/country_wildfield_landscape_1600x1200/wallpapers/1680x1050/country_field_landscape_photo_EA52064.jpg", "第二张");
        ImageAndText imageText5=new ImageAndText("http://hiphotos.baidu.com/%D3%C0%C9%FA%E6%E4%D5%BE/pic/item/ee9a097f600bf4770dd7da02.jpg", "第二张");
        list.add(imageText1);
        list.add(imageText2);
        list.add(imageText3);
        list.add(imageText4);
        list.add(imageText5);
        
        ListView listView=(ListView) findViewById(R.id.listView);
        adapter=new ImageAndTextListAdapter(this, list, listView);
        listView.setAdapter(adapter);
       
    }
}