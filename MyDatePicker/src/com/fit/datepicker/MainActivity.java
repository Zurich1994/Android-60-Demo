package com.fit.datepicker;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    int mYear,mMonth,mDay;
    Button btn;
    TextView dateDisplay;
    final int DATE_DIALOG=1;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
         btn=(Button) findViewById(R.id.dateChoose);
         dateDisplay=(TextView) findViewById(R.id.dateDisplay);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG);
			}
		});
        
        final Calendar ca=Calendar.getInstance();
        mYear=ca.get(Calendar.YEAR);
        mMonth=ca.get(Calendar.MONTH);
        mDay=ca.get(Calendar.DAY_OF_MONTH);
    }
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id){
		case DATE_DIALOG:
			return new DatePickerDialog(this, mdateListener,mYear,mMonth, mDay);
		}
		return null;
	}
	
	public void display(){
		dateDisplay.setText(new StringBuffer().append(mMonth+1).append("-").append(mDay).append("-").append(mYear).append(" "));
	}
	
	private DatePickerDialog.OnDateSetListener mdateListener=new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			display();
		}
	};
}