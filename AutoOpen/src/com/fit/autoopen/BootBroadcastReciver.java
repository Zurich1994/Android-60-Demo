package com.fit.autoopen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReciver extends BroadcastReceiver {
	 static final String ACTION = "android.intent.action.BOOT_COMPLETED";
 	@Override
 	public void onReceive(Context context, Intent intent) {
 		// TODO Auto-generated method stub
 		if(intent.getAction().equals(ACTION)){
 			Intent i=new Intent(context, MainActivity.class);
 			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 			context.startActivity(i);
 		}
 	}

}
