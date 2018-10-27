package com.fit.pho;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;


import biz.source_code.base64Coder.Base64Coder;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private  ImageView newBit,oldBit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        oldBit=(ImageView) findViewById(R.id.oldbit);
        newBit=(ImageView) findViewById(R.id.newbit);
        
//    	try {
    		//Transform Bitmap into String
//            File file=new File(Environment.getExternalStorageDirectory()+"/ssss.jpg");
//			FileInputStream in = new FileInputStream(file);
//			Bitmap bit=BitmapFactory.decodeStream(in);
//			oldBit.setImageBitmap(bit);
//			ByteArrayOutputStream out=new ByteArrayOutputStream();
//			bit.compress(Bitmap.CompressFormat.JPEG,100,out);
////			String 	bitString=byte2hex(out.toByteArray());
//			String imageString=Base64.encode(out.toByteArray());
//			System.out.println("------------------------"+imageString);
			//Transform String into Bitmap
//			byte [] newbyte=hex2byte(bitString);
//			Bitmap newBitmap=BitmapFactory.decodeByteArray(newbyte,0,newbyte.length);
//			newBit.setImageBitmap(newBitmap);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
        
        
//			String picString=pictureToString(Environment.getExternalStorageDirectory()+"/ssss.jpg");
//			System.out.println("++++"+picString);
//			StringTopicture(Environment.getExternalStorageDirectory()+"/ssdd.jpg",picString);
        String imageString=GetImageStr(Environment.getExternalStorageDirectory()+"/ssss.jpg");
        System.out.println("----------------------"+imageString);
    }
    // 二进制转字符串
    public static String byte2hex(byte[] b) 
    {
       StringBuffer sb = new StringBuffer();
       String stmp = "";
       for (int n = 0; n < b.length; n++) {
        stmp = Integer.toHexString(b[n] & 0XFF);
        if (stmp.length() == 1){
        	sb.append("0" + stmp);
        }else{
        	sb.append(stmp);
        }
        
       }
       return sb.toString();
    }
 // 字符串转二进制
    public static byte[] hex2byte(String str) { 
        if (str == null)
         return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
         return null;
        byte[] b = new byte[len / 2];
        try {
         for (int i = 0; i < str.length(); i += 2) {
          b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
         }
         return b;
        } catch (Exception e) {
         return null;
        }
    }
   //将sdcard中的图片转化成字符串
//	public String pictureToString(String path) {
//		byte[] len = new byte[1024];
//		FileInputStream fis=null;
//		try {
//			fis = new FileInputStream(new File(path));
//			while (true) {
//				try {
//					int i = fis.read(len);
//					if (i == -1)
//						break;
//					ByteBuffer bb = ByteBuffer.wrap(len, 0, i);
//					byte []imageByte=bb.array();
//					String imageString = Base64.encode(imageByte);
//					System.out.println("-----------------------------------------"+imageString);
//					return imageString;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}finally{
//			if(fis!=null){
//				try {
//					fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
	//将字符串转化成图片存入到目录中
	public void StringTopicture(String path, String pic) {
		FileOutputStream fos=null;
		try {
			byte[] b = hex2byte(pic);
		fos = new FileOutputStream(new File(path));
				fos.write(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	    public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理   
	        if(imgFilePath == null || imgFilePath == ""){   
	            return "";   
		       }   
		       File file = new File(imgFilePath);   
		      if(!file.exists()){   
		            return "";   
		       }   
		      byte[] data = null;   
	       // 读取图片字节数组   
		       try {   
		           InputStream in = new FileInputStream(imgFilePath);   
		            data = new byte[in.available()];   
	         in.read(data);   
		            in.close();   
		       } catch (IOException e) {   
		            e.printStackTrace();   
		       }   
		      // 对字节数组Base64编码   
		       String imageString=new String(Base64Coder.encode(data));
		       return imageString;// 返回Base64编码过的字节数组字符串   
	    } 
}