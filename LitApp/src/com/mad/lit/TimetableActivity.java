package com.mad.lit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



public class TimetableActivity extends ListActivity {
	
	String[] values = new String[] { "Day 1 - Monday",
			 "Day 2 - Tuesday",
			 "Day 3 - Wednesday",
			 "Day 4 - Thursday",
			 "Day 5 - Friday"
			};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        ListView listView = getListView();
        View headerView = getLayoutInflater().inflate(R.layout.day, null);
        listView.addHeaderView(headerView);        
        
        CustomArrayAdapter listAdapter = new CustomArrayAdapter(this, values);
		setListAdapter(listAdapter);		
    }    	
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	//Toast.makeText(this, values[position-1], Toast.LENGTH_LONG).show();
    	Intent timetable = new Intent(TimetableActivity.this, SelectedDayActivity.class);
    	
		//put number to string
		String StringPos =Integer.toString(position);
		//get the text in list from text view and put it to string
		
		Log.i("VALUES", "logining value of view "+StringPos);
		timetable.putExtra("extra", StringPos);
		TimetableActivity.this.startActivityForResult(timetable, position);
		//TimetableActivity.this.startActivity(timetable);
    	//Intent i =  new Intent(TimetableActivity.this, SelectedDayActivity.class);
    }
}