package com.fit.sensor;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	TextView  tvX,tvY,tvZ;
	SensorManager sensorManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tvX=(TextView) findViewById(R.id.tvX);
        tvY=(TextView) findViewById(R.id.tvY);
        tvZ=(TextView) findViewById(R.id.tvZ);
        
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	sensorManager.registerListener(mySensorListener, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_UI);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	sensorManager.unregisterListener(mySensorListener);
    }
    
    private SensorListener mySensorListener=new SensorListener() {
		
		@Override
		public void onSensorChanged(int sensor, float[] values) {
			//�ж��Ƿ�Ϊ���ٶȴ������仯����
			if(sensor==SensorManager.SENSOR_ACCELEROMETER){
				tvX.setText("X���ϵļ��ٶ�Ϊ��"+values[0]);
				tvY.setText("Y���ϵļ��ٶ�Ϊ��"+values[1]);
				tvZ.setText("Z���ϵļ��ٶ�Ϊ��"+values[2]);
			
			}
		}
		
		@Override
		public void onAccuracyChanged(int sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
}