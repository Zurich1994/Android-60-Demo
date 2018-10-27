package com.android.border;

import com.android.border.MyService.MyBinder;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private static final String RECEIVERTAG = "unRegisterBorder";
	private static final String CONNECTSERVICE = "ConnectService";
	private static final String DISCONNECTSERVICE = "DisConnectService";
	private static final String UNBINDSERVICE = "unbindService";
	private MyBorderReceiver receiver;
	boolean mBound = false;
	private MyService myService;
	private int mymsg;
	private TextView view;

	public class MyBorderReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			mymsg = intent.getIntExtra("msg", 2);
			view.setText("�㲥�ӷ���˽��չ�������Ϣ�ǣ�" + mymsg);
			Message message = new Message();
			Bundle data = new Bundle();
			data.putInt("progress", mymsg);
			message.setData(data);
			message.what = 1;
			handler.sendMessage(message);
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				int progress = msg.getData().getInt("progress");
				// Toast.makeText(MainActivity.this,"++++"+progress,Toast.LENGTH_LONG).show();
				// ��ȡ֪ͨ�����������ڷ���֪ͨ
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				// ʵ����Notification�������������Ϣ����
				Notification notice = new Notification(R.drawable.icon,
						"����һ��֪ͨ��Ϣ", System.currentTimeMillis());
				// //�����չ����Ϣ���������
				// String noticeTitle="֪ͨ�ı���";
				// String noticeContent="֪ͨ��չ������";
				// //ͨ����ϢҪ�򿪵�Ӧ�ó���
				// Intent intent=new Intent("com.fit.demo.notice");
				// //�ȴ��û���������Ϣ
				// PendingIntent
				// pending=PendingIntent.getActivity(MainActivity.this,0,
				// intent, 0);
				// //�����е���Ϣ���ݷŵ�Notification��
				// notice.setLatestEventInfo(getApplicationContext(),
				// noticeTitle, noticeContent, pending);
				// //��Ӧ�ó������Ϣͼ����ʧ
				// notice.flags|=Notification.FLAG_AUTO_CANCEL;
				// //ͨ����Ϣ������������Ϣ
				// //manager.notify(1,notice);
				//

				// //���ڲ��Ϸ�����Ϣ�����Ḳ����ǰ����Ϣ
				// manager.notify(1,notice);
				RemoteViews view = new RemoteViews(getPackageName(),
						R.layout.notice);
				view.setProgressBar(R.id.progress, 100, progress, false);
				view.setTextColor(R.id.myview, Color.RED);
				view.setTextViewText(R.id.myview, "���ȣ�    " + progress + "%");
				notice.contentView = view;
				Intent intent1 = new Intent("com.fit.demo.notice");
				PendingIntent pending = PendingIntent.getActivity(
						MainActivity.this, 0, intent1, 0);
				notice.contentIntent = pending;
				
				// ��Ӧ�ó������Ϣͼ����ʧ
				notice.flags |= Notification.FLAG_AUTO_CANCEL;
				// ͨ����Ϣ������������Ϣ
				manager.notify(1, notice);
				break;
			}
		}
	};

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		view = (TextView) findViewById(R.id.view);
		receiver = new MyBorderReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.android.myborder");
		this.registerReceiver(receiver, filter);

		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(MainActivity.this, MyService.class);
		this.bindService(intent, conn, Context.BIND_AUTO_CREATE);
		this.startService(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(receiver);
		Log.i(RECEIVERTAG, "the receiver have been unRegistered!");
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mBound) {
			unbindService(conn);
			Log.i(UNBINDSERVICE, "the service have been unbinded!");
			mBound = false;
		}

	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(DISCONNECTSERVICE, "service have disConnect!");
			mBound = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(CONNECTSERVICE, "service have connected!");
			MyBinder binder = (MyBinder) service;
			myService = binder.getService();
			mBound = true;
		}
	};
}