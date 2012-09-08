package com.mad.lou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
	static int THIS_VERSION = 1;
	
	public MyDBHelper(Context ctx){
		super(ctx, "stats_list_db6", null, THIS_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v("DB", "Creating schema");
		
		String sql="create table pedometerstats(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"steps TEXT NOT NULL, distance TEXT, pace TEXT, speed TEXT, calories TEXT, datestat TEXT)";
		db.execSQL(sql);
		
		String sql2="create table foodstats(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"date TEXT NOT NULL, fruit DOUBLE, veg DOUBLE, dairy DOUBLE, meat DOUBLE, grain DOUBLE)";
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		/*if(oldVersion>2){
			Log.v("DB", "Upgraging from version older than 2");
			String sql="alter table pedometerstats add column datestat TEXT";
			db.execSQL(sql);
		}*/

	}

}
