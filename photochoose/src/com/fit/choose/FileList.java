package com.fit.choose;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class FileList {
	
	public  List<Map<String,Object>> fileList(String filepath){
		FileInputStream in=null;
		List<Map<String,Object>> photosList=new ArrayList<Map<String,Object>>();
		
		Bitmap bitmap=null;
		File dir=new File(filepath);       
		File[] files=dir.listFiles();
		
		for(int i=0;i<files.length;i++){
			File file=files[i];
		
			if(file.isDirectory() && file.listFiles()!=null){
				
				fileList(file.getAbsolutePath());
			}else if((file.getName()).endsWith(".jpg")){
			try {
				Map<String,Object> map=new HashMap<String, Object>();
				in=new FileInputStream(file);
				BitmapFactory.Options options=new BitmapFactory.Options();
				options.inSampleSize=2;
				bitmap=BitmapFactory.decodeStream(in,null,options);
				map.put("name",file.getName());
				map.put("photo", bitmap);
				photosList.add(map);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			}
		}
		return photosList;
	}

}
