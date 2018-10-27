package com.fit.testlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MinnActivity extends Activity {
    /** Called when the activity is first created. */
	String laystyle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        
        String []items=getResources().getStringArray(R.array.spinnerdata);
        Spinner sp=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MinnActivity.this,R.layout.spinneritema,items);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView viewa=(TextView) arg1.findViewById(R.id.viewa);
				laystyle=viewa.getText().toString();
				
				
				//Toast.makeText(MinnActivity.this,laystyle,Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        Button btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle extras=new Bundle();
				extras.putString("style",laystyle);
				Intent intent=new Intent(MinnActivity.this, styleActivity.class);
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
    }
}