package com.mad.lit;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
	static int THIS_VERSION = 1;
	
	public MyDBHelper(Context ctx){
		super(ctx, "todo_list_db2", null, THIS_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v("DB", "Creating schema");
		
		String sql="create table reminderlist(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"item_name TEXT NOT NULL, todo TEXT)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
