package com.mad.lit;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LitAppActivity extends Activity {
    /** Called when the activity is first created. */
	
	Button btn_profile;
	Button btn_ca;
	Button btn_maps;
	Button btn_notes;
	Button btn_timetable;
	Button btn_moodle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /**
         * Creating all buttons instances
         * */
        //main screen profile button
        btn_profile = (Button) findViewById(R.id.btn_profile);
        
        // main ca button
        btn_ca = (Button) findViewById(R.id.btn_ca);
        
        // main maps button
        btn_maps = (Button) findViewById(R.id.btn_maps);
        
        //main notes button
        btn_notes = (Button) findViewById(R.id.btn_notes);
        
        // main timetable button
        btn_timetable = (Button) findViewById(R.id.btn_timetable);
        
        // main moodle button
        btn_moodle = (Button) findViewById(R.id.btn_moodle);
        
        /**
         * Handling all button click events
         * */
        btn_profile.setOnClickListener((OnClickListener) buttonhandler);
        btn_ca.setOnClickListener((OnClickListener) buttonhandler);
        btn_maps.setOnClickListener((OnClickListener) buttonhandler);
        btn_notes.setOnClickListener((OnClickListener) buttonhandler);
        btn_timetable.setOnClickListener((OnClickListener) buttonhandler);
        btn_moodle.setOnClickListener((OnClickListener) buttonhandler);
    }
    
    View.OnClickListener buttonhandler=new View.OnClickListener() { 

 	   // Now I need to determine which button was clicked, and which intent or activity to launch.         
 	      public void onClick(View v) {
 	    	  switch(v.getId()) { 
     		    	
 			case R.id.btn_profile:
 				
 				showToast("feature not implemented");
	        	
     		//Intent profile = new Intent(LitAppActivity.this, ProfileActivity.class);
     		//LitAppActivity.this.startActivity(profile);
     		break;
     		      
     		case R.id.btn_ca:    
  		    
     			showToast("feature not implemented");
     		//Intent ca = new Intent(LitAppActivity.this, CaCalcActivity.class);
  		    //LitAppActivity.this.startActivity(ca);
  		    break;
  		    
     		case R.id.btn_maps:    
      		Intent maps = new Intent(LitAppActivity.this,  LitMapsActivity.class);
      		LitAppActivity.this.startActivity(maps);
      		break;
      		    
     		case R.id.btn_notes: 
     		//	showToast("feature not implemented");
     			Intent notes = new Intent(LitAppActivity.this, NotesActivity.class);
     			LitAppActivity.this.startActivity(notes);
      		break;
      		    
     		case R.id.btn_timetable:    
      		
     		//	showToast("feature not implemented");
     		Intent timetable = new Intent(LitAppActivity.this, TimetableActivity.class);
      		LitAppActivity.this.startActivity(timetable);
      		break;
      		    
     		case R.id.btn_moodle:
     			showToast("feature not implemented");
      		//Intent moodle = new Intent(LitAppActivity.this, MoodleActivity.class);
      		//LitAppActivity.this.startActivity(moodle);
      		break;
  		    
 	   }
     		   
     		   }
     	};
    	private void showToast(String msg){
    		Context context = getApplicationContext();
    		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
    		toast.show();
    	}
 
}