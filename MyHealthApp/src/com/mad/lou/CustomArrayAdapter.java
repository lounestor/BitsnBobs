package com.mad.lou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	private Context context;
	private String[] stringValues;
	
	public CustomArrayAdapter(Context context, String[] stringValues) {
		super(context, R.layout.chart_list_item, stringValues);
		this.context = context;
		this.stringValues = stringValues;
	}
	
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view;
		if (convertView == null) {			
			view = inflater.inflate(R.layout.chart_list_item, parent, false);
		}
		else
			view = convertView;
		TextView text1 = (TextView) view.findViewById(R.id.DateItem);
		text1.setText(stringValues[position]);
		LinearLayout layout_item = (LinearLayout) view.findViewById(R.id.layout_item);
		
		if (position % 2 == 0) {
			layout_item.setBackgroundColor(context.getResources().getColor(R.color.blue));
			text1.setTextColor(context.getResources().getColor(R.color.white));
		} else {
			layout_item.setBackgroundColor(context.getResources().getColor(R.color.white));
			text1.setTextColor(context.getResources().getColor(R.color.darkgrey));
		}				
		return view;
	}
}