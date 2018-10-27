package com.fit.timepicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	TextView time;
	TimePicker picker;
	Button display;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        time=(TextView) findViewById(R.id.time);
        picker=(TimePicker) findViewById(R.id.picker);
        display=(Button) findViewById(R.id.display);
        
        picker.setIs24HourView(true);
        picker.setCurrentHour(5);
        picker.setCurrentMinute(30);
        picker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				time.setText(hourOfDay+"-"+minute+":"+view.getCurrentHour()+":"+view.getCurrentMinute());
			}
		});
        
        
        display.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setTitle(picker.getCurrentHour()+":"+picker.getCurrentMinute());
			}
		});
    }
}