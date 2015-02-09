package com.example.swiprefreshlayoutdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{

	
	protected static final int REFRESH_COMPLETE = 0;
	private ListView mListView;
	private SwipeRefreshLayout mSwipeLayout;
	private ArrayAdapter mAdapter;
	private int count ;
	
     private List<String> mDatas = new ArrayList<String>(Arrays.asList("Android"));
    
     
     private Handler mHandler = new Handler()  
     {  
         public void handleMessage(android.os.Message msg)  
         {  
             switch (msg.what)  
             {  
                case REFRESH_COMPLETE:  
                count++;
                mDatas.addAll(Arrays.asList("新增加第"+count+"条信息"));  
                mAdapter.notifyDataSetChanged();  
                mSwipeLayout.setRefreshing(false);  
                break;  
             }  
         };  
     };  
     
     
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
	     mListView = (ListView)findViewById(R.id.id_listview);
	     mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly); 
	     mSwipeLayout.setOnRefreshListener(this);
	     mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
	                android.R.color.holo_orange_light, android.R.color.holo_red_light);
	     
	      mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);  
	        mListView.setAdapter(mAdapter);    

	}
	
	
	public void onRefresh() {
		// TODO Auto-generated method stub
		 mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000); 
	}
	
	

}
