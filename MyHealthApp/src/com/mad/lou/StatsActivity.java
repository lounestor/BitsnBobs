package com.mad.lou;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class StatsActivity extends Activity {

	ViewFlipper mViewFlipper;
	ListView mStatsListView;
	EditText mStepsStats;
	EditText mDistanceStats;
	EditText mCaloriesStats;
	EditText mPaceStats;
	EditText mSpeedStats;
	TextView mDateStats;

	Button mAddEditButton;
	Button mDeleteButton;
	SQLiteDatabase mDb;
	Cursor mCursor;
	String mEditItemId;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);
        
        mViewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);        
        mStatsListView = (ListView) findViewById(R.id.StatsListView);    
        
             
        //steps
        mStepsStats = (EditText) findViewById(R.id.StepsStats);
        //distance
        mDistanceStats = (EditText) findViewById(R.id.DistanceStats);
        
        //pace
        mPaceStats = (EditText) findViewById(R.id.PaceStats);
        //speed
        mSpeedStats = (EditText) findViewById(R.id.SpeedStats);
        
      //speed
        mDateStats = (TextView) findViewById(R.id.DateStats);
       
        //calories
        mCaloriesStats = (EditText) findViewById(R.id.CaloriesStats);
        
        mAddEditButton = (Button) findViewById(R.id.AddEditButton);
        
        mDeleteButton = (Button) findViewById(R.id.AddDeleteButton);
        
        Button cancelButton = (Button) findViewById(R.id.CancelButton);
        
        cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Show main screen
				
			}
		});
        
        mStatsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long itemId) {
				
				
				mEditItemId = String.valueOf(itemId);				
				
				String vals[] = {mEditItemId};
				String cols[] = {"steps", "distance", "pace", "speed", "calories","datestat"};
				Cursor c = mDb.query("pedometerstats", cols, "_id=?", vals, null ,null ,null);
				
				
				if(c.moveToFirst()){
					
					mStepsStats.setText(c.getString(0));
					mDistanceStats.setText(c.getString(1));
					mPaceStats.setText(c.getString(2));
					mSpeedStats.setText(c.getString(3));
					mCaloriesStats.setText(c.getString(4));
					//mDateStats.setText(c.getString(6));
					mViewFlipper.setDisplayedChild(1);	
				}
				c.close();
				
			}
			
		});
        
      	    		
		     	
        mDeleteButton.setText("Delete");
        mDeleteButton.setOnClickListener(new OnClickListener(){
        public void onClick(View v) {
    		//ContentValues cv.re = new ContentValues(); 
  		   	Log.i("[VALUES TO DELETE]", "");
        	
  		   	mDb.delete("pedometerstats", "_id=?", new String[] {String.valueOf(mEditItemId)});
        	
    		
    		mCursor.requery();
    		mViewFlipper.setDisplayedChild(0);	
    	}	
    });
        

        mAddEditButton.setText("Update");
        
        mAddEditButton.setOnClickListener(new OnClickListener(){
        	
      
        	public void onClick(View argo0) {
        		ContentValues cv = new ContentValues(); 
        			
            	cv.put("steps", mStepsStats.getText().toString());
        		cv.put("distance", mDistanceStats.getText().toString());
        		cv.put("pace", mPaceStats.getText().toString());
        		cv.put("speed", mSpeedStats.getText().toString());
        		cv.put("calories", mCaloriesStats.getText().toString());
        		//cv.put("datestat", mDateStats.getText().toString());
        		
        		
        		String args[] = {String.valueOf(mEditItemId)};
        		mDb.update("pedometerstats", cv, "_id=?", args);
        		
        		mCursor.requery();
        		mViewFlipper.setDisplayedChild(0);	
        	}	
        }); 
        
        
               
        MyDBHelper mh = new MyDBHelper(this);
        mDb = mh.getWritableDatabase();
        mCursor = mDb.query("pedometerstats", null, null,null,null,null,"_id desc");
        
        startManagingCursor(mCursor);
        int views[] = {R.id.StepsStats, R.id.DistanceStats, R.id.PaceStats, R.id.SpeedStats, R.id.CaloriesStats, R.id.DateStats};
        String cols[] = {"steps", "distance", "pace", "speed", "calories","datestat"};
		SimpleCursorAdapter ca = new SimpleCursorAdapter(this, R.layout.statslist, mCursor, cols, views);
		if(mCursor.moveToFirst())
			Log.v("my list"," values found");
			
		else
			Log.v("my list"," not  found");
		
        mStatsListView.setAdapter(ca);
        
        
    }
	   protected void onDestroy() {
       	super.onDestroy();
       	mDb.close();
       }
    
	    
	   
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater i = getMenuInflater();
    	i.inflate(R.menu.menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.AddItem) {
    		
    	showAddScreen();	
    		
    	}
    	return false;
    }
	private void showAddScreen() {
		mAddEditButton.setText("Add");
		mAddEditButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				ContentValues cv = new ContentValues();
				
				cv.put("steps", mStepsStats.getText().toString());
        		cv.put("distance", mDistanceStats.getText().toString());
        		cv.put("pace", mPaceStats.getText().toString());
        		cv.put("speed", mSpeedStats.getText().toString());
        		cv.put("calories", mCaloriesStats.getText().toString());
        		cv.put("datestat", mDateStats.getText().toString());
				
				mDb.insert("pedometerstats", null, cv);
				
				mCursor.requery();
				mViewFlipper.setDisplayedChild(0);
				
			}
		});
		mViewFlipper.setDisplayedChild(1);
	}
}