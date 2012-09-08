package com.mad.lou;


import com.mad.lou.chart.*;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyFoodChart extends ListActivity {
		
	SQLiteDatabase mDb;
	Cursor mCursor;
	TextView mDate;
	String[] array;
	String mSelect;
	double fruit = 1,veg = 1,dairy = 1,meat = 1,grain = 1;
	 
	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mDate = (TextView)findViewById(R.id.DateItem);
	    MyDBHelper mh = new MyDBHelper(this);
        mDb = mh.getWritableDatabase();
        mCursor = mDb.query("foodstats", null, null,null,null,null,"date desc");
       
        array = new String[mCursor.getCount()];
        int i = 0;
        while(mCursor.moveToNext()){
            String uname = mCursor.getString(mCursor.getColumnIndex("date"));
            array[i] = uname;
            i++;
        }         
        Log.i("DB........","results of test is "+ array);
	    CustomArrayAdapter listAdapter = new CustomArrayAdapter(this, array);
		setListAdapter(listAdapter);	
		mCursor.requery();
	  
	    
	    }	  
	 
	  @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	super.onListItemClick(l, v, position, id);
	    	Toast.makeText(this, array[position], Toast.LENGTH_LONG).show();
	    	//onclick get the value on the array and bundle it
	    	
	    	
	    	mSelect = String.valueOf(id);
	    	String vals[] = {mSelect};	    	
	    	String cols[] = {"fruit", "veg", "dairy", "meat", "grain"};
			mCursor = mDb.query("foodstats", cols, "_id=?", vals, null ,null ,null);
			Log.i("Here DB........","results of test is "+ array);
			if(mCursor.moveToFirst()){
				fruit = mCursor.getDouble(mCursor.getColumnIndex("fruit"));
				veg = mCursor.getDouble(mCursor.getColumnIndex("veg"));
				dairy = mCursor.getDouble(mCursor.getColumnIndex("dairy"));
				meat = mCursor.getDouble(mCursor.getColumnIndex("meat"));
				grain = mCursor.getDouble(mCursor.getColumnIndex("grain"));
			}
			mCursor.close();
						
								
			Intent achartIntent = new FoodChart().execute(this, fruit, veg, dairy, meat, grain);
			startActivity(achartIntent);
	 
	}
	  protected void onDestroy() {
	       	super.onDestroy();
	       	mDb.close();
	       }
}

