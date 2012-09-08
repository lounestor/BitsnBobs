package com.mad.lou;

import com.mad.lou.StatsActivity;
import com.mad.lou.MyMapsActivity;
import com.mad.lou.RecipeActivity;
import com.mad.lou.BmiActivity;
import com.mad.lou.MusicActivity;
import com.mad.lou.pedometer.PedometerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MyHealthActivity extends Activity {
	
	Button btn_stats;
	Button btn_bmi;
	Button btn_maps;
	Button btn_pedometer;
	Button btn_recipes;
	Button btn_music;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myhealth_layout);
        
        /**
         * Creating all buttons instances
         * */
        //main screen events button
        btn_stats = (Button) findViewById(R.id.btn_stats);
        
        // main screen gallery button
        btn_bmi = (Button) findViewById(R.id.btn_bmi);
        
        // main screen maps button
        btn_maps = (Button) findViewById(R.id.btn_maps);
        
        //main screen pedometer button
        btn_pedometer = (Button) findViewById(R.id.btn_pedometer);
        
        // main screen recipes button
        btn_recipes = (Button) findViewById(R.id.btn_recipes);
        
        // main screen music button
        btn_music = (Button) findViewById(R.id.btn_music);
        
        /**
         * Handling all button click events
         * */
        btn_stats.setOnClickListener((OnClickListener) buttonhandler);
        btn_bmi.setOnClickListener((OnClickListener) buttonhandler);
        btn_maps.setOnClickListener((OnClickListener) buttonhandler);
        btn_pedometer.setOnClickListener((OnClickListener) buttonhandler);
        btn_recipes.setOnClickListener((OnClickListener) buttonhandler);
        btn_music.setOnClickListener((OnClickListener) buttonhandler);
    }
    
    View.OnClickListener buttonhandler=new View.OnClickListener() { 

 	   // Now I need to determine which button was clicked, and which intent or activity to launch.         
 	      public void onClick(View v) {
 	    	  switch(v.getId()) { 
     		    	
 			case R.id.btn_stats:    
     		Intent events = new Intent(MyHealthActivity.this, StatsActivity.class);
     		MyHealthActivity.this.startActivity(events);
     		break;
     		      
     		case R.id.btn_bmi:    
  		    Intent bmi = new Intent(MyHealthActivity.this, BmiActivity.class);
  		    MyHealthActivity.this.startActivity(bmi);
  		    break;
  		    
     		case R.id.btn_maps:    
      		Intent maps = new Intent(MyHealthActivity.this,  MyMapsActivity.class);
      		MyHealthActivity.this.startActivity(maps);
      		break;
      		    
     		case R.id.btn_pedometer:    
      		Intent pedometer = new Intent(MyHealthActivity.this, PedometerActivity.class);
      		MyHealthActivity.this.startActivity(pedometer);
      		break;
      		    
     		case R.id.btn_recipes:    
      		Intent recipe = new Intent(MyHealthActivity.this, RecipeActivity.class);
      		MyHealthActivity.this.startActivity(recipe);
      		break;
      		    
     		case R.id.btn_music:    
      		Intent music = new Intent(MyHealthActivity.this, MusicActivity.class);
      		MyHealthActivity.this.startActivity(music);
      		break;
  		    
 	   }
     		   
     		   }
     	};
     
 
}