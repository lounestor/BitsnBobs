package com.mad.lou;

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
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RecipeActivity extends Activity {
	
	static String LIST_URL = "http://www.jdmacdonald.com/stuff/recipe.xml";
	ArrayList<HashMap<String, String>> recipeList;
	Handler mHandler;
	ClassesDownloadTask mTask;
	ProgressDialog mProgress;
	Intent i;
	ListView list;
	
	public Object onRetainNonConfigurationInstance(){
		Log.v("XML", "Saving data");
		return recipeList;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v("HIT", "onCreate triggered");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.recipie_layout);
		
		loadRecipeLists();
		displayRecipeLists();
	
		mHandler = new Handler(){
	    public void handleMessages(Message mgs){
	    displayRecipeLists();
	    mProgress.dismiss();
	    
	    if(mgs.what == 1){
			new AlertDialog.Builder(RecipeActivity.this)
			.setTitle("Error")
			.setPositiveButton(android.R.string.ok, null)
			.setMessage("Failed to download packages")
			.show();
	    }else{
	    	displayRecipeLists();
			}
	    }
	};
	//startBackgroundTask();
	displayRecipeLists();
	
	
	list = (ListView) findViewById(R.id.list);	
	list.setOnItemClickListener(new OnItemClickListener(){
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			
			Intent i = new Intent(RecipeActivity.this, ViewRecipe.class);
			
			@SuppressWarnings("unchecked")
			HashMap <String, String> rec_list = (HashMap <String, String> ) parent.getItemAtPosition(position);
			String s = rec_list.get("title");			
			i.putExtra("instruction", s);
			Log.v("XML", "title of recipe.....: " + s);
			startActivity(i);
		
		}
	});
	
	
	
}

	private void startBackgroundTask(){
    	mProgress = ProgressDialog.show(this, "Loading recipe packages",
				"Please wait...", true);
    	mProgress.setCancelable(true);
    	mProgress.setOnCancelListener(new OnCancelListener(){
    		
    		public void onCancel(DialogInterface args0){
    		RecipeActivity.this.finish();
    		}
    	});
		mProgress.show();
    	mTask = new ClassesDownloadTask();
		mTask.execute();
	}
	
	private void loadRecipeLists(){
	
	try{
	        Log.v("HIT", "loadRecipeLists triggered");
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
	       
	        recipeList = new ArrayList<HashMap<String, String>>();
	        XmlPullParserFactory parseCreator = XmlPullParserFactory.newInstance();
	        XmlPullParser parser = parseCreator.newPullParser();
	        parser.setInput(is, null);
	       
	        int event;
	        String title = null;
	        String time = null;
	        String serves = null;
	        String thumb_url = null;
	        String tag = null;
	        while((event = parser.next()) != XmlPullParser.END_DOCUMENT){
	                if(event == XmlPullParser.START_TAG){
	                        tag = parser.getName();
	                        if("dish".equals(tag)){
	                                title = time = serves = thumb_url = "";
	                        }
	                } else if(event == XmlPullParser.TEXT){
	                        if("title".equals(tag)){
	                                title = parser.getText();
	                                Log.v("XML", "loaded package: " + title);
	                        } else if("time".equals(tag)){
	                                time = parser.getText();
	                                Log.v("XML", "loaded package: " + time);
	                        }else if ("serves".equals(tag)){
	                                serves = parser.getText();
	                                Log.v("XML", "loaded package: " + serves);
	                        }else if ("thumb_url".equals(tag)){
	                        	thumb_url = parser.getText();
	                        	 Log.v("XML", "loaded package: " + thumb_url);
	                        }
	                } else if (event == XmlPullParser.END_TAG){
	                        if ("dish".equals(parser.getName())){
	                                HashMap<String, String> map = new HashMap<String, String>();
	                                
	                                map.put("title", title);
	                                Log.v("XML", "adding title to map: " + title);
	                                map.put("time", time);
	                                Log.v("XML", "adding time to map: " + time);
	                                map.put("serves", serves);
	                                Log.v("XML", "adding serves to map: " + serves);
	                                map.put("thumb_url", thumb_url);
	                                Log.v("XML", "adding thumb to map: " + thumb_url);
	                                recipeList.add(map);
	                                Log.v("XML", "loaded package: map = " + map);
	                        }
	                        tag = null;
	                }
	        }
	       
		}catch (Exception e){
	        Log.v("XML", "Error fetching recipe.xml", e);
	        //Toast.
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

	private void displayRecipeLists(){
		if(!isNetworkAvailable()){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(RecipeActivity.this)
			.setTitle("NO INTERNET CONNECTION")
			.setMessage("You must have internet connection to avail of this service")
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {   
			    public void onClick(DialogInterface dialog, int which) {  
			    	Intent myIntent = new Intent(((Dialog) dialog).getContext(), MyHealthActivity.class); 
			        startActivity(myIntent);
			        return;   
			        }         
			    });
			alertDialog.show();
        	
        }
		else{Log.v("HIT", "displayRecipePackages triggered");
		String from[] = {"title", "time", "serves"};
		int to[] = {R.id.title, R.id.time, R.id.serves};
		SimpleAdapter a  = new SimpleAdapter(this, recipeList, R.layout.list_row, from, to);
		ListView l = (ListView) findViewById (R.id.list);
		l.setAdapter(a);
		}
	
	
	}
	
	
	public class ClassesDownloadTask extends AsyncTask<Void,Void,Void>{
		boolean bFailed = false;
		@Override
		protected Void doInBackground(Void... params){
			Log.v("XML", "Loading data from AsyncTask");
			loadRecipeLists();
			if(!isCancelled()){
				mHandler.sendEmptyMessage(bFailed ? 1:0);
			}
			
			return null;	
			
		}
			
	}
}

