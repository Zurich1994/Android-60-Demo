package com.fit.text;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	EditText first,second,third;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        first=(EditText) findViewById(R.id.first);
        second=(EditText) findViewById(R.id.second);
        third=(EditText) findViewById(R.id.third);
       
        myTextWather textWather = new myTextWather();
        first.addTextChangedListener(textWather);
        
        
//        first.addTextChangedListener(new TextWatcher() {
//			
//        
//        	
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				// TODO Auto-generated method stub
//				String firstText=first.getText().toString();
//				if(firstText.length()==3){
//					second.requestFocus();
//				}
//				
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//			
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//				String firstText=first.getText().toString();
//				if(firstText.length()==3){
//					second.requestFocus();
//				}
//			}
//		});
        
    }
    
    
    class  myTextWather  implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			if (s==first) {
				String firstText=first.getText().toString();
				if(firstText.length()==3){
					second.requestFocus();
				}
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s==first) {
				String firstText=first.getText().toString();
				if(firstText.length()==3){
					second.requestFocus();
				}
				
			}
		}
    	
    }
}