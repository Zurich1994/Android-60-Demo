package com.android.progress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	ProgressDialog dialog;
	TextView tv;
	int fileSize;
	int downLoadFileSize;
	String fileEx, fileNa, filename;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				dialog.setMax(fileSize);
			case 1:
				dialog.setProgress(downLoadFileSize);
				// int result = downLoadFileSize * 100 / fileSize;
				// dialog.setMessage("正在下载......."+result);
				break;
			case 2:
				Toast.makeText(MainActivity.this, "文件下载完成", 1).show();
				break;
			case -1:
				String error = msg.getData().getString("error");
				Toast.makeText(MainActivity.this, error, 1).show();
				break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new ProgressDialog(MainActivity.this);
				dialog.setMessage("正在下载.......");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setCancelable(false);
				dialog.show();

				new Thread() {
					public void run() {
						try {

							down_file(
									"http://wallpaper.pocketdigi.com/upload/1/bigImage/1284565196.jpg",
									"/sdcard/");
							dialog.dismiss();

							// 下载文件，参数：第一个URL，第二个存放路径
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});

	}

	public void down_file(String url, String path) throws IOException {

		// 下载函数
		filename = url.substring(url.lastIndexOf("/") + 1);
		// 获取文件名
		URL myURL = new URL(url);
		URLConnection conn = myURL.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();
		this.fileSize = conn.getContentLength();// 根据响应获取文件大小
		if (this.fileSize <= 0)
			throw new RuntimeException("无法获知文件大小 ");
		if (is == null)
			throw new RuntimeException("stream is null");
		FileOutputStream fos = new FileOutputStream(path + filename);
		// 把数据存入路径+文件名
		byte buf[] = new byte[1024];
		downLoadFileSize = 0;
		sendMsg(0);
		do

		{

			// 循环读取
			int numread = is.read(buf);
			if (numread == -1) {
				break;
			}
			fos.write(buf, 0, numread);
			downLoadFileSize += numread;
			sendMsg(1);// 更新进度条
		} while (true);
		sendMsg(2);// 通知下载完成
		try {
			is.close();
		} catch (Exception ex) {
			Log.e("tag", "error: " + ex.getMessage(), ex);
		}
	}

	private void sendMsg(int flag) {
		Message msg = new Message();
		msg.what = flag;
		handler.sendMessage(msg);

	}
}
