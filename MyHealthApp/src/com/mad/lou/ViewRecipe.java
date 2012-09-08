package com.mad.lou;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class ViewRecipe extends Activity {
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewrecipe);
	        WebView mWebView ;
	      //  TextView showrecipe = (TextView)findViewById(R.id.display_recipe);
	        mWebView = (WebView) findViewById(R.id.webview);
	        mWebView.getSettings().setJavaScriptEnabled(true);
	        String title_recipe = getIntent().getStringExtra("instruction");
	        Log.v("XML", "recieved data is: " + title_recipe);
	        //showrecipe.setText(title_recipe);
	        	if(title_recipe.contentEquals("Potato and pea samosas")){
	        		mWebView.loadUrl("http://www.jdmacdonald.com/stuff/samosas.html");
	        	}else if (title_recipe.contentEquals("Blueberry Maple smoothie")){
	        		mWebView.loadUrl("http://www.jdmacdonald.com/stuff/smoothie.html");
	        	}else if(title_recipe.contentEquals("spring lamb casserole")){
	        		mWebView.loadUrl("http://www.jdmacdonald.com/stuff/casserole.html");
	        	}else if(title_recipe.contentEquals("Pasta with tomato pesto")){	        	
	        		mWebView.loadUrl("http://www.jdmacdonald.com/stuff/pasta.html");
	        	}
	        
	 }

}
