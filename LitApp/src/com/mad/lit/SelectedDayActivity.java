package com.mad.lit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedDayActivity extends Activity {
    /** Called when the activity is first created. */
	
	static String LIST_URL = "http://dl.dropbox.com/u/12470092/images/timetable.xml";
	ArrayList<HashMap<String, String>> timetableList;
	Handler mHandler;
	ClassesDownloadTask mTask;
	ProgressDialog mProgress;
	
	public Object onRetainNonConfigurationInstance(){
		Log.v("XML", "Saving data");
		return timetableList;
	}
	
	Intent intent;
	 @Override
    public void onCreate(Bundle savedInstanceState) {
		 Log.v("HIT", "onCreate triggered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day);
        TextView showDay = (TextView)findViewById(R.id.display_day);
       
        //sting postition from timtable activity:keyword "extra"
        String Name = getIntent().getStringExtra("extra"); 
       
        if(Name.contains("1")){    	   
    	   showDay.setText("Monday");
    	   LIST_URL = "http://dl.dropbox.com/u/12470092/images/timetable.xml";
       }else if(Name.equals("2")){
    	   showDay.setText("Tuesday");
    	   LIST_URL = "http://dl.dropbox.com/u/12470092/images/timetable2.xml";
       }else if(Name.equals("3")){
    	   showDay.setText("Wednesday");
    	   LIST_URL = "http://dl.dropbox.com/u/12470092/images/timetable3.xml";
       }else if(Name.equals("4")){
    	   showDay.setText("Thursday");
    	   LIST_URL = "http://dl.dropbox.com/u/12470092/images/timetable4.xml";
       }else if(Name.equals("5")){
    	   showDay.setText("Friday");
    	   LIST_URL = "http://dl.dropbox.com/u/12470092/images/timetable5.xml";
       }
       
       
       
       loadTimetableLists();
		displayTimetableLists();
		
		mHandler = new Handler(){
		    public void handleMessages(Message mgs){
		    displayTimetableLists();
		    mProgress.dismiss();
		    if(mgs.what == 1){
				new AlertDialog.Builder(SelectedDayActivity.this)
				.setTitle("Error")
				.setPositiveButton(android.R.string.ok, null)
				.setMessage("Failed to download packages")
				.show();
		    }else{
		    	displayTimetableLists();
				}
		    }
		};
		//startBackgroundTask();
		displayTimetableLists();
		
	}
	 private void startBackgroundTask(){
	    	mProgress = ProgressDialog.show(this, "Loading timetable packages",
					"Please wait...", true);
	    	mProgress.setCancelable(true);
	    	mProgress.setOnCancelListener(new OnCancelListener(){
	    		
	    		public void onCancel(DialogInterface args0){
	    			SelectedDayActivity.this.finish();
	    		}
	    	});
			mProgress.show();
	    	mTask = new ClassesDownloadTask();
			mTask.execute();
		}
		
		private void loadTimetableLists(){
		
		try{
		        Log.v("HIT", "loadTimetableLists triggered");
		        HttpParams params = new BasicHttpParams();
		        HttpConnectionParams.setConnectionTimeout(params, 10000);
		        HttpConnectionParams.setSoTimeout(params, 1000);
		        
		        DefaultHttpClient client = new DefaultHttpClient(params);
		        HttpGet get = new HttpGet(LIST_URL);
		        HttpResponse res = client.execute(get);
		        int code = res.getStatusLine().getStatusCode();
		       
		        if (code != 200){
		                throw new Exception("Invalid status code: " + code);
		        }
		        Log.v("XML", "Successfully fetched XML file");
		  
		        InputStream is = res.getEntity().getContent();
		       
		        timetableList = new ArrayList<HashMap<String, String>>();
		        XmlPullParserFactory parseCreator = XmlPullParserFactory.newInstance();
		        XmlPullParser parser = parseCreator.newPullParser();
		        parser.setInput(is, null);
		       
		        int event;
		        String name = null;
		        String time = null;
		        String type = null;
		        String room = null;
		        String tag = null;
		       
		        while((event = parser.next()) != XmlPullParser.END_DOCUMENT){
		                if(event == XmlPullParser.START_TAG){
		                        tag = parser.getName();
		                        if("class".equals(tag)){
		                                name = time = type = room = "";
		                        }
		                } else if(event == XmlPullParser.TEXT){
		                        if("time".equals(tag)){
		                                time = parser.getText();
		                        } else if("name".equals(tag)){
		                                name = parser.getText();
		                        }else if ("type".equals(tag)){
		                                type = parser.getText();
		                        }else if ("type".equals(tag)){
	                                type = parser.getText();
	                        }else if ("room".equals(tag)){
	                            room = parser.getText();
	                    }
		                } else if (event == XmlPullParser.END_TAG){
		                	//Name = getIntent().getStringExtra("extra"); 
		                        if ("class".equals(parser.getName())){
		                                HashMap<String, String> map = new HashMap<String, String>();
		                               
		                                map.put("name", name);
		                                map.put("time", time);
		                                map.put("type", type);
		                                map.put("room", room);
		                                timetableList.add(map);
		                                Log.v("XML", "loaded package: " + name);
		                        }
		                        tag = null;
		                }
		        }
		       
			}catch (Exception e){
		        Log.v("XML", "Error fetching tuesday.xml", e);
			}
		
		}
		
		public boolean isNetworkAvailable() {
		    ConnectivityManager cm = (ConnectivityManager) 
		    	getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		    // if no network is available networkInfo will be null
		    // otherwise check if we are connected
		    if (networkInfo != null && networkInfo.isConnected()) {
		        return true;
		    }
		    return false;
		}

	
		
				
		private void displayTimetableLists(){
			if(!isNetworkAvailable()){
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectedDayActivity.this)
				.setTitle("NO INTERNET CONNECTION")
				.setMessage("You must have internet connection to avail of this service")
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {   
				    public void onClick(DialogInterface dialog, int which) {  
				    	Intent myIntent = new Intent(((Dialog) dialog).getContext(), LitAppActivity.class); 
				        startActivity(myIntent);
				        return;   
				        }         
				    });
				alertDialog.show();
	        	
	        }
			
		Log.v("HIT", "displayTimetablePackages triggered");
		String from[] = {"name", "time", "type","room"};
		int to[] = {R.id.tName, R.id.tTime, R.id.typeTimetable,R.id.roomTimetable};
		SimpleAdapter a  = new SimpleAdapter(this, timetableList, R.layout.timetable, from, to);
		ListView l = (ListView) findViewById (R.id.timetblList);
		l.setAdapter(a);
		}

		
		
		public class ClassesDownloadTask extends AsyncTask<Void,Void,Void>{
			boolean bFailed = false;
			@Override
			protected Void doInBackground(Void... params){
				Log.v("XML", "Loading data from AsyncTask");
				loadTimetableLists();
				if(!isCancelled()){
					mHandler.sendEmptyMessage(bFailed ? 1:0);
				}
				
				return null;	
				
			}
				
		}
       
       

             

	 
	 private void showToast(String mgs){
	    	Context context = getApplicationContext();
	    	
	    		Toast toast = Toast.makeText(context, mgs, Toast.LENGTH_LONG);
	    		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0,0);
	    		
	    		toast.show();
	    	
	    	
	    }
}
