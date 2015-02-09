package com.example.pulltorefreshdemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {
	private List<String> testList = null;
	Context mContext = null;
	LayoutInflater layoutFlate;

	public TestAdapter(List<String> testList, Context context) {
		
		this.testList = testList;
		this.mContext = context;
		layoutFlate = LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return testList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return testList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if(convertView == null){
			holder = new Holder();
			convertView = layoutFlate.inflate(R.layout.test_item, null);
			holder.test_tv = (TextView)convertView.findViewById(R.id.test_tv);
			convertView.setTag(holder);
		}else{
			holder = (Holder)convertView.getTag();
		}
		holder.test_tv.setText(testList.get(position));
		return convertView;
	}

	class Holder{
		TextView test_tv;
	}
	
	
}
