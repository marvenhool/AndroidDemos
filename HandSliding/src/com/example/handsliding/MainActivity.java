package com.example.handsliding;

import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class MainActivity extends Activity {
	String tag = this.getClass().getName();
	PullToRefreshListView pullrefresh_lv = null;
	TestAdapter testAdapter = null;
	LinkedList<String> testArr = null;
	int i = 0;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				i++;
				testArr.addFirst("刷新了第" + i + "条信息｡");
				pullrefresh_lv
						.onRefreshComplete(getString(R.string.pull_to_refresh_update)
								+ new Date().toLocaleString());
				pullrefresh_lv.onRefreshComplete();
				testAdapter.notifyDataSetChanged();
				break;
  
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		pullrefresh_lv = (PullToRefreshListView) findViewById(R.id.pullrefresh_lv);
		testArr = new LinkedList<String>();
		testArr.add("下拉可以刷新！");
		testAdapter = new TestAdapter(testArr, this);
		pullrefresh_lv.setAdapter(testAdapter);

		pullrefresh_lv
				.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
					public void onRefresh() {
						handler.sendEmptyMessageDelayed(0, 2000);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
