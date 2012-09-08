package com.mad.lit;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class SimpleArrayAdapter implements ListAdapter {
	private String TAG = "SimpleArrayAdapter"; //for logging
	private String[] mData;
	private Context mCtx;
	
	public SimpleArrayAdapter(Context ctx, String[] data){
		
		mData = data;
		mCtx = ctx;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.v(TAG, "getCount()");
		if(mData ==null)
			return 0;
		else
			return mData.length;
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		Log.v(TAG, "getItem()");
		return mData[pos];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.v(TAG, "getView()");
		TextView txt = null;
		if(convertView !=null){
			txt = (TextView) convertView;
		}else{
			txt = new TextView(mCtx);
		}
		txt.setText(mData[position]);
			return txt;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return true;
	}

}
