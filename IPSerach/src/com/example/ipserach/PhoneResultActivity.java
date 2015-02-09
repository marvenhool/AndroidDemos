package com.example.ipserach;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PhoneResultActivity extends Activity {
	
	private String searchPhoneNo;
	private JSONObject result;
	private TextView phoneNo;
	private TextView service;
	private TextView province;
	Handler handler=new Handler();
	

    Runnable   runnableUi=new  Runnable(){  
        @Override  
        public void run() {  
			try {
				service.setText("運営会社:"+DoStrProcess(result.getString("catName").trim()));
				province.setText("都道府県:"+DoStrProcess(result.getString("province").trim()));

			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        
        private String DoStrProcess(String fromStr)
        {
        	if (fromStr != null && fromStr != ""){
        		return fromStr;
        	   }
        	else{
        		return "未知";
        	}
        }

    } ;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phoneresult);
		
		Intent intent = getIntent();  
		//データを取得
		searchPhoneNo = intent.getStringExtra("searchPhoneNo");

		phoneNo = (TextView)findViewById(R.id.phoneNo);
		phoneNo.setText("携帯番号:"+searchPhoneNo);
		
		service = (TextView)findViewById(R.id.service);
		province = (TextView)findViewById(R.id.province); 

		 new Thread(){
	            @Override
	            public void run(){
	            	try {
						result = GetPhoneNoLocation(searchPhoneNo);
						handler.post(runnableUi);
						//IPEditText.setText(result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }.start();
	}
	
	private JSONObject GetPhoneNoLocation(String searchPhoneNo) throws Exception
	{

        List<NameValuePair> params = new ArrayList<NameValuePair>();  
       // params.add(new BasicNameValuePair("format", "json"));  
        params.add(new BasicNameValuePair("tel", searchPhoneNo));   
        String url = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?" + URLEncodedUtils.format(params, HTTP.UTF_8);  
        JSONObject  returnStr = get(url);  
        return returnStr;

	}
	
	 public static JSONObject get(String url) {  
        
	        HttpClient client = new DefaultHttpClient();  
	        HttpGet get = new HttpGet(url);  
	        String ipLore = null;
	        JSONObject json = null;
	        try {  
	            HttpResponse res = client.execute(get);  
	            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
	            	
	            	ipLore = EntityUtils.toString(res.getEntity()); 
	            	ipLore = ipLore.replace("__GetZoneResult_ =", "");
	            	json = new JSONObject(ipLore);  

	            }  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	              
	        } finally{  
	            client.getConnectionManager().shutdown();  
	        }  
	        return json;  
	    }
	 
}
