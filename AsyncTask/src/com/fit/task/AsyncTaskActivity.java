package com.fit.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity {
	/** Called when the activity is first created. */
	ProgressBar progressBar;
	TextView textView;
	long length;
	int count = 0;
	int result ;
	ProgressDialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView=(TextView) findViewById(R.id.textView);
//		progressBar = (ProgressBar) this.findViewById(R.id.progressbar);
		Button button = (Button) this.findViewById(R.id.download);
		button.setOnClickListener(new View.OnClickListener() {

			String url = "http://wallpaper.pocketdigi.com/upload/1/bigImage/1284565196.jpg";
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog = new ProgressDialog(AsyncTaskActivity.this);
				dialog.setMessage("正在下载.......");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setCancelable(false);
				dialog.show();
				new DownloadTask().execute(url);
				
//				progressBar.setVisibility(View.VISIBLE);
//				progressBar.setProgress(0);
				
			}
			
		});
		
	}
	private class DownloadTask extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpGet httpRequest = new HttpGet(params[0]);
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				HttpEntity entity = httpResponse.getEntity();
				length = entity.getContentLength();
				dialog.setMax((int) length);
				//progressBar.setMax((int) length);
				System.out.println("length:" + length);
				InputStream inputStream = entity.getContent();
				byte[] b = new byte[1024];
				int readedLength = -1;
				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsoluteFile(), "song.mp3");
				OutputStream outputStream = new FileOutputStream(file);
				
				while ((readedLength = inputStream.read(b)) != -1) {
					outputStream.write(b, 0, readedLength);
					count += readedLength;
					// 调用了这个方法之后会触发onProgressUpdate(Integer... values)
					publishProgress(count);
				}
				dialog.dismiss();

				inputStream.close();
				outputStream.close();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			dialog.setProgress(count);
			// System.out.println("onProgressUpdate");
			// 用来更新界面
//			textView.setText((count * 100 / length)+"%");
//			progressBar.setProgress(values[0]);
			super.onProgressUpdate(values);
		}
		
	}
	
}
