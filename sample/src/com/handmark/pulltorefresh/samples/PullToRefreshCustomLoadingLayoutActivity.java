/*
 * Copyright (C) 2014 Naver Business Platform Corp.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.handmark.pulltorefresh.samples;

import java.util.Arrays;
import java.util.LinkedList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PullToRefreshCustomLoadingLayoutActivity extends Activity {
	private static final String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance",
		"Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
		"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
		"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
		"Allgauer Emmentaler" };

	private PullToRefreshListView mPullRefreshListView;
	private LinkedList<String> mListItems;
	private ArrayAdapter<String> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_custom_loadinglayout);
		initPullToRefresh();
	}

	private void initPullToRefresh() {
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.ptrListView);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				Log.d(PullToRefreshCustomLoadingLayoutActivity.class.getCanonicalName(), "onRefreshing..." + label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		final ListView actualListView = mPullRefreshListView.getRefreshableView();

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
		actualListView.setAdapter(mAdapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {   

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Log.d(PullToRefreshCustomLoadingLayoutActivity.class.getCanonicalName(), "GetDataTask.doInBackground");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {

			mListItems.addFirst("New Item");
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			Log.d(PullToRefreshCustomLoadingLayoutActivity.class.getCanonicalName(), "onRefreshComplete");
			mPullRefreshListView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}
