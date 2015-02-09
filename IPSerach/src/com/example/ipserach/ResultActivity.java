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

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity {
	
	private String ipAddress;
	private JSONObject result;
	private TextView ipAddressText;
	private TextView countryText;
	private TextView provinceText;
	private TextView cityText;
	Handler handler=new Handler();
	

    Runnable   runnableUi=new  Runnable(){  
        @Override  
        public void run() {  
			try {
				countryText.setText("国家:"+DoStrProcess(result.getString("country").trim()));
				provinceText.setText("都道府県:"+DoStrProcess(result.getString("province").trim()));
				cityText.setText("市:"+DoStrProcess(result.getString("city").trim()));

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
		setContentView(R.layout.activity_result);
		
		Intent intent = getIntent();  
		//データを取得
		ipAddress = intent.getStringExtra("ipAddress");
		ipAddressText = (TextView)findViewById(R.id.ipAddress);
		ipAddressText.setText("IPアドレス:"+ipAddress);
		
		countryText = (TextView)findViewById(R.id.country); 
		provinceText = (TextView)findViewById(R.id.province); 
		cityText = (TextView)findViewById(R.id.city);

		 new Thread(){
	            @Override
	            public void run(){
	            	try {
						result = GetIPLocation(ipAddress);
						handler.post(runnableUi);
						//IPEditText.setText(result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }.start();
	}
	
	private JSONObject GetIPLocation(String ipAddress) throws Exception
	{

        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("format", "json"));  
        params.add(new BasicNameValuePair("ip", ipAddress));   
        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?" + URLEncodedUtils.format(params, HTTP.UTF_8);  
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
