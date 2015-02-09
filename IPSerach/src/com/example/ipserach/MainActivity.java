package com.example.ipserach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	   	Button button01=(Button)findViewById(R.id.Button01); 
	   	button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	//Toast.makeText(getActivity(), "rootView.findViewById|fragment_main - -#", Toast.LENGTH_LONG).show();
	            Intent intent=new Intent();
	            intent.setClass(MainActivity.this,ResultActivity.class);
			   	EditText IPEditText = (EditText)findViewById(R.id.sample_edit_text); 
				
			   	String ipAddress = IPEditText.getText().toString().trim();
	            intent.putExtra("ipAddress", ipAddress);
	            MainActivity.this.startActivity(intent);
            	}
        });
	   	
	   	
	   	Button button02=(Button)findViewById(R.id.Button02); 
	   	button02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
	            Intent intent=new Intent();
	            intent.setClass(MainActivity.this,PhoneResultActivity.class);
			   	EditText PhoneEditText = (EditText)findViewById(R.id.phone_edit_text); 
				
			   	String PnoneNo = PhoneEditText.getText().toString().trim();
	            intent.putExtra("searchPhoneNo", PnoneNo);
	            MainActivity.this.startActivity(intent);
            	}
        });
		
	}
 
    
	protected void onResume(){
		
		super.onResume();
		EditText IPEditText = (EditText)findViewById(R.id.sample_edit_text); 
		IPEditText.setText("".toString());
	}
	
	protected void onPause(){
		
		super.onPause();
	}

}
