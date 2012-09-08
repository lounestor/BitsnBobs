package com.mad.lit;


import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.ViewFlipper;

public class NotesActivity extends Activity {

	ViewFlipper mViewFlipper;
	ListView mtodoListView;
	EditText mtodoItem;
	EditText mtodoTime;
	Button mAddEditButton;
	Button mDeleteButton;
	SQLiteDatabase mDb;
	Cursor mCursor;
	String mEditItemId;
	Button cancelButton;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        
        mViewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);
        mtodoListView = (ListView) findViewById(R.id.todoListView);
        mtodoItem = (EditText) findViewById(R.id.todoItem);
        mtodoTime = (EditText) findViewById(R.id.todoTime);
        mAddEditButton = (Button) findViewById(R.id.AddEditButton);
        
        cancelButton = (Button) findViewById(R.id.CancelButton);
        mDeleteButton = (Button) findViewById(R.id.DeleteButton);
        
        cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Show main screen
				 mViewFlipper.setDisplayedChild(0); 
			}
		});
        
       
        
        mtodoListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long itemId) {
				mEditItemId = String.valueOf(itemId);
				
				String vals[] = {mEditItemId};
				String cols[] = {"item_name", "todo"};
				Cursor c = mDb.query("reminderlist", cols, "_id=?", vals, null ,null ,null);
				
				if(c.moveToFirst()){
					mtodoItem.setText(c.getString(0));
					mtodoTime.setText(c.getString(1));
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
        	
  		   	mDb.delete("reminderlist", "_id=?", new String[] {String.valueOf(mEditItemId)});
        	
    		
    		mCursor.requery();
    		mViewFlipper.setDisplayedChild(0);	
    	}	
    });
        

        
        mAddEditButton.setText("Update");
        
        mAddEditButton.setOnClickListener(new OnClickListener(){

        	public void onClick(View argo0) {
        		ContentValues cv = new ContentValues();
        		cv.put("item_name", mtodoItem.getText().toString());
        		cv.put("todo", mtodoTime.getText().toString());
        		
        		String args[] = {String.valueOf(mEditItemId)};
        		mDb.update("reminderlist", cv, "_id=?", args);
        		
        		mCursor.requery();
        		mViewFlipper.setDisplayedChild(0);	
        	}	
        });
       

        
        MyDBHelper mh = new MyDBHelper(this);
        mDb = mh.getWritableDatabase();
        mCursor = mDb.query("reminderlist", null, null,null,null,null,"item_name desc");
        startManagingCursor(mCursor);
        int views[] = {R.id.NameView, R.id.ReminderView};
        String cols[] = {"item_name" , "todo" };
        SimpleCursorAdapter ca = new SimpleCursorAdapter(this, R.layout.todo_item, mCursor, cols, views);
        mtodoListView.setAdapter(ca);
        
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
				cv.put("item_name", mtodoItem.getText().toString());
				cv.put("todo", mtodoTime.getText().toString());
				mDb.insert("reminderlist", null, cv);
				
				mCursor.requery();
				mViewFlipper.setDisplayedChild(0);
				
			}
		});
		mViewFlipper.setDisplayedChild(1);
	}
}
