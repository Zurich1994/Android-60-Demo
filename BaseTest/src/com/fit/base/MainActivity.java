package com.fit.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import base64.Base64;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String imageString = GetImageStr(Environment
				.getExternalStorageDirectory() + "/icon.png");
		Log.i("imageString", imageString);
	}

	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		if (imgFilePath == null || imgFilePath == "") {
			return "";
		}
		File file = new File(imgFilePath);
		if (!file.exists()) {
			return "";
		}
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = getByte(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 String imageString = Base64.encodeToString(data, Base64.DEFAULT);
		return imageString;// 返回Base64编码过的字节数组字符串
	}

	public static byte[] getByte(InputStream in) {
		if (in == null) {
			return null;
		}
		int sumSize = 0;
		List<byte[]> totalBytes = new ArrayList<byte[]>();
		byte[] buffer = new byte[1024];
		int length = -1;
		try {
			while ((length = in.read(buffer)) != -1) {
				sumSize += length;
				byte[] tmp = new byte[length];
				System.arraycopy(buffer, 0, tmp, 0, length);
				totalBytes.add(tmp);
			}
			byte[] data = new byte[sumSize];
			int start = 0;
			for (byte[] tmp : totalBytes) {
				System.arraycopy(tmp, 0, data, start, tmp.length);
				start += tmp.length;
			}
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}