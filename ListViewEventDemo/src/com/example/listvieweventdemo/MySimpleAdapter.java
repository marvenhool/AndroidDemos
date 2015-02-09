package com.example.listvieweventdemo;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Map<String, Object>> list;
	private int layoutID;
	private String flag[];
	private int ItemIDs[];

	public MySimpleAdapter(Context context, List<Map<String, Object>> list,
			int layoutID, String flag[], int ItemIDs[]) {
		this.mInflater = LayoutInflater.from(context);
		this.list = list;
		this.layoutID = layoutID;
		this.flag = flag;
		this.ItemIDs = ItemIDs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
	  return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	 public View getView(int position, View convertView, ViewGroup parent) {  
	        convertView = mInflater.inflate(layoutID, null);  
	        for (int i = 0; i < flag.length; i++) {
	            if (convertView.findViewById(ItemIDs[i]) instanceof TextView) {  
	                TextView tv = (TextView) convertView.findViewById(ItemIDs[i]);  
	                tv.setText((String) list.get(position).get(flag[i]));  
	            }else{  
	                //遍历所有控件，映射到每一个List中去设置数据
	            }  
	        }
	        //存在监听事件的控件是不能直接映射的，在addListener方法中增加监听器就好了
	        addListener(convertView,position);  
	        return convertView;  
		
	    }
	 
	  public void addListener(View convertView, final int position) {  
		  
		  Button btn_set = (Button)convertView.findViewById(R.id.view_btn);
		  
		  btn_set.setOnClickListener(  
	                new View.OnClickListener() {  
	                    @Override  
	                    public void onClick(View v) {  
//	                        new AlertDialog.Builder(MainActivity.ma)    
//	                        .setMessage("当前点击的Item是"+ position)  
//	                        .show();
	                        //list.remove(position);
	                        MainActivity.dataList.remove(position);
	                        MainActivity.adapter.notifyDataSetChanged();
	                    }  
	                });  
	    }
}
