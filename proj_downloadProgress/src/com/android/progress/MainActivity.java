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
				// dialog.setMessage("��������......."+result);
				break;
			case 2:
				Toast.makeText(MainActivity.this, "�ļ��������", 1).show();
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
				dialog.setMessage("��������.......");
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

							// �����ļ�����������һ��URL���ڶ������·��
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

		// ���غ���
		filename = url.substring(url.lastIndexOf("/") + 1);
		// ��ȡ�ļ���
		URL myURL = new URL(url);
		URLConnection conn = myURL.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();
		this.fileSize = conn.getContentLength();// ������Ӧ��ȡ�ļ���С
		if (this.fileSize <= 0)
			throw new RuntimeException("�޷���֪�ļ���С ");
		if (is == null)
			throw new RuntimeException("stream is null");
		FileOutputStream fos = new FileOutputStream(path + filename);
		// �����ݴ���·��+�ļ���
		byte buf[] = new byte[1024];
		downLoadFileSize = 0;
		sendMsg(0);
		do

		{

			// ѭ����ȡ
			int numread = is.read(buf);
			if (numread == -1) {
				break;
			}
			fos.write(buf, 0, numread);
			downLoadFileSize += numread;
			sendMsg(1);// ���½�����
		} while (true);
		sendMsg(2);// ֪ͨ�������
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
