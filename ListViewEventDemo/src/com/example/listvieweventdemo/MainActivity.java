package com.example.listvieweventdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity  {
	
	public static MainActivity ma;
	public static List<Map<String, Object>> dataList;
	public static MySimpleAdapter adapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ma = this;
		dataList = getData();
		adapter = new MySimpleAdapter(this, dataList,
                R.layout.listviewsimpleadapter, new String[] {"title", "info"},
                new int[] {R.id.title, R.id.info });
		
		setListAdapter(adapter);
        
	}
	
	 private List<Map<String, Object>> getData() {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("title", "陈二宝");
	        map.put("info", "蘑菇屯儿当红明星");
	        list.add(map);

	        map = new HashMap<String, Object>();
	        map.put("title", "李大嘴");
	        map.put("info", "马兰坡最著名厨子");
	        list.add(map);

	        map = new HashMap<String, Object>();
	        map.put("title", "ヤマダ");
	        map.put("info", "日本最大の電気販売商");
	        list.add(map);

	        map = new HashMap<String, Object>();
	        map.put("title", "你好");
	        map.put("info", "最后的灰姑娘");
	        list.add(map);

	        map = new HashMap<String, Object>();
	        map.put("title", "hello");
	        map.put("info", "every thing");
	        list.add(map);

	        map = new HashMap<String, Object>();
	        map.put("title", "world");
	        map.put("info", "hello world");
	        list.add(map);

	        return list;
	    }

}
