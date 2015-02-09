package com.example.pulltorefreshdemo;

import java.util.Date;
import java.util.LinkedList;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class MainActivity extends Activity {

	String tag = this.getClass().getName();
	PullToRefreshListView pullrefresh_listView = null;
	Button pullrefresh_Btn = null;
	TestAdapter testAdapter = null;
	LinkedList<String> testArr = null;
	int i = 0;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				i++;
				testArr.addFirst("刷新第" + i + "条");
				pullrefresh_listView
						.onRefreshComplete(getString(R.string.pull_to_refresh_update)
								+ new Date().toLocaleString());
				pullrefresh_listView.onRefreshComplete();
				testAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		pullrefresh_listView = (PullToRefreshListView) findViewById(R.id.pulltoRefresh_Listview);
		testArr = new LinkedList<String>();
		testArr.add("下拉可以更新哦~~要不要试试看");
		testAdapter = new TestAdapter(testArr, this);
		pullrefresh_listView.setAdapter(testAdapter);
		// pullrefresh_listView.setOnItemClickListener(new OnItemClickListener()
		// {
		//
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		// // TODO Auto-generated method stub
		// handler.sendEmptyMessageDelayed(0, 2000);
		// }
		// });

		pullrefresh_listView
				.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {

					public void onRefresh() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessageDelayed(0, 2000);
					}
				});
	}

}
