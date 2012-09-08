package com.mad.lou;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;



public class FoodDiaryActivity extends Activity implements AdapterView.OnItemSelectedListener {
	/** Called when the activity is first created. */

	ArrayAdapter<String> amountAdapter;

	// declare the references for the UI elements
	Spinner mySpinner,mySpinner2,mySpinner3,mySpinner4;
	TextView bmiValueText;
	TextView bmiDescriptionText;
	ProgressBar redBar, greenBar, blueBar;
	BmiProgress bmiBar;
	ProgressBar pg;
	float[] roundedCorners;
	String redbar,orangebar,bluebar,greenbar;
	String myColor;
	SQLiteDatabase mDb;
	Cursor mCursor;
	String currentDateTimeString;
	Button mySave;
	Button myShowChart;
	private TextView selection,selection2,selection3,selection4,selection5;
	private String items[] = {"0","1","2","3","4","5", "6","7","8","9","10"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fooddiary);
		
		selection=(TextView)findViewById(R.id.selection);
		selection2=(TextView)findViewById(R.id.selection2);
		selection3=(TextView)findViewById(R.id.selection3);
		selection4=(TextView)findViewById(R.id.selection4);
		selection5=(TextView)findViewById(R.id.selection5);
		ImageButton info1=(ImageButton)findViewById(R.id.info1);
		ImageButton info2=(ImageButton)findViewById(R.id.info2);
		ImageButton info3=(ImageButton)findViewById(R.id.info3);
		ImageButton info4=(ImageButton)findViewById(R.id.info4);
		ImageButton info5=(ImageButton)findViewById(R.id.info5);
		
		info1.setOnClickListener((OnClickListener) buttonhandler);
		info2.setOnClickListener((OnClickListener) buttonhandler);
		info3.setOnClickListener((OnClickListener) buttonhandler);
		info4.setOnClickListener((OnClickListener) buttonhandler);
		info5.setOnClickListener((OnClickListener) buttonhandler);
        
		mySave = (Button)findViewById(R.id.button_savedata);
		myShowChart = (Button)findViewById(R.id.button_chart);
		myShowChart.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			Intent i = new Intent(FoodDiaryActivity.this, MyFoodChart.class);
    			FoodDiaryActivity.this.startActivity(i);
    			
    		}
    	});
		
	    Spinner spin=(Spinner)findViewById(R.id.spinner);
	    Spinner spin2=(Spinner)findViewById(R.id.spinner2);
	    Spinner spin3=(Spinner)findViewById(R.id.spinner3);
	    Spinner spin4=(Spinner)findViewById(R.id.spinner4);
	    Spinner spin5=(Spinner)findViewById(R.id.spinner5);
	    spin.setOnItemSelectedListener(this);
	    spin2.setOnItemSelectedListener(this);
	    spin3.setOnItemSelectedListener(this);
	    spin4.setOnItemSelectedListener(this);
	    spin5.setOnItemSelectedListener(this);
	    
	    ArrayAdapter<String> aa=new ArrayAdapter<String>(this,
	                              android.R.layout.simple_spinner_item,
	                              items);
	    
	    	aa.setDropDownViewResource(
	    			android.R.layout.simple_spinner_dropdown_item);
	    	spin.setAdapter(aa);
	    
	    ArrayAdapter<String> aa2=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                items);

	    aa2.setDropDownViewResource(
	    		android.R.layout.simple_spinner_dropdown_item);
	    spin2.setAdapter(aa2);
	    
	    ArrayAdapter<String> aa3=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                items);

	    aa3.setDropDownViewResource(
	    		android.R.layout.simple_spinner_dropdown_item);
	    		spin3.setAdapter(aa3);
	    
	    ArrayAdapter<String> aa4=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                items);

	    aa4.setDropDownViewResource(
	    		android.R.layout.simple_spinner_dropdown_item);
	    spin4.setAdapter(aa4);
	    
	    ArrayAdapter<String> aa5=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                items);

	    aa5.setDropDownViewResource(
	    		android.R.layout.simple_spinner_dropdown_item);
	    spin5.setAdapter(aa5);
	    
	    MyDBHelper mh = new MyDBHelper(this);
    	mDb = mh.getWritableDatabase();
    	
    	mCursor = mDb.query("foodstats", null, null,null,null,null,"_id desc");
    	startManagingCursor(mCursor);
    	mySave.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			
    			currentDateTimeString = DateFormat.getDateInstance().format(new Date());
    			ContentValues cv = new ContentValues();
    			//selection1 works
    			double fruit = Double.parseDouble(selection.getText().toString());
    			double veg = Double.parseDouble(selection2.getText().toString());
    			double meat = Double.parseDouble(selection2.getText().toString());
    			double dairy = Double.parseDouble(selection2.getText().toString());
    			//selection5 works
    			double grain = Double.parseDouble(selection5.getText().toString());
    			Log.i("VALUES", "logining fruit and veg VALUES"+fruit+veg+meat+grain);
    			//cv.put("date", currentDateTimeString);
    			cv.put("date", currentDateTimeString);
    			cv.put("fruit", fruit);
    			cv.put("veg", veg);
    			cv.put("meat", meat);
    			cv.put("dairy", dairy);
    			cv.put("grain", grain);
    			//cv.put("veg", selection2.getText().toString());
    			//cv.put("dairy", selection3.getText().toString());
    			//cv.put("meat", selection4.getText().toString());
    			//cv.put("grain", selection5.getText().toString());
    			
    			
    			mDb.insert("foodstats", null, cv);
				
    			mCursor.requery();//
    			
    		
    			mCursor.close();
    			Log.v("your data ","has been sent to db");

    		}
    	});
	  }
	View.OnClickListener buttonhandler=new View.OnClickListener() { 
				
	 	   // Now I need to determine which button was clicked, and which intent or activity to launch.         
	 	      public void onClick(View v) {
	 	    	 
	 	    	  switch(v.getId()) { 
	     		    	
	 			case R.id.info1: 
	 				
	 				AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoodDiaryActivity.this)
	 				.setTitle("Fruit")
	 				.setMessage("it is important to have a balanced diet. Fruits are a rich source of all vitamins es" +
	 						"pecially C and E, containing folate which help build red blood cells minerals like potassium and" +
	 						"iodine, enzymes and photochemicals, all of which combine to improve health\n" +
	 						"Fruits are also contain natural sugar and fiber which gives us energy\n" +
	 						"The high water content and fiber makes them easily digestible\n" +
	 						"THe roughage aids the digestion on other foods consumer" +
	 						"\n\n and they taste great")
	 				.setPositiveButton("got it", new DialogInterface.OnClickListener() {
	 							public void onClick(DialogInterface dialog,int id) {
	 								
	 							}
	 						  });
	 						alertDialog.show();
	     		break;
	     		      
	     		case R.id.info2:    
	     			AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(FoodDiaryActivity.this)
	 				.setTitle("Vegtables")
	 				.setMessage("Vegetables are excellent sources of vitamins A, E and C, fiber, " +
	 						"folic acid and potassium. Potassium is essential in controlling blood pressure. " +
	 						"Folic acid helps form red blood cells in the body. Vitamin A is essential for skin and eye health. " +
	 						"Vitamin C helps the body heal cuts and wounds and keeps gums and teeth healthy. Vitamin E guards vitamin A " +
	 						"and essential fatty acids from cell oxidation.\nPeople who eat more vegetables have a lower risk of heart disease or stroke. " +
	 						"\nAccording to a study conducted at the Harvard School of Public health, persons who eat more than five servings of leafy green " +
	 						"\nvegetables such as spinach, greens, broccoli, cabbage and cauliflower have a 20 percent lower risk of " +
	 						"developing coronary heart disease compared with those who ate fewer than three servings per day.")
	 				.setPositiveButton("got it", new DialogInterface.OnClickListener() {
	 							public void onClick(DialogInterface dialog,int id) {
	 								
	 							}
	 						  });
	 						alertDialog2.show();
	  		    break;
	  		    
	     		case R.id.info3:    
	     			AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(FoodDiaryActivity.this)
	 				.setTitle("Dairy")
	 				.setMessage("Dairy plays an important role in protecting our bodies from injury and " +
	 						"disease as well as providing our bodies with vital nutrients. \nCalcium and vitamin D " +
	 						"are the key nutrients found in dairy. Many Americans do not get enough calcium or vitamin D " +
	 						"in their diets. \nIf you do not get enough of these vitamins and minerals, you could be at a higher risk for " +
	 						"developing osteoporosis or low bone mass. \nOsteoporosis is a disease that is characterized by low " +
	 						"bone mass and weak bones. Having this disease can make it easier to break bones. " +
	 						"Also, your bones are responsible for allowing your body to move, supporting your body and" +
	 						" maintaining your immunity. If your bones are diseased, it will be very difficult to function. " +
	 						"This is why making sure you are getting enough calcium and vitamin D on a daily basis is important.")
	 				.setPositiveButton("got it", new DialogInterface.OnClickListener() {
	 							public void onClick(DialogInterface dialog,int id) {
	 								
	 							}
	 						  });
	 						alertDialog3.show();
	      		break;
	      		    
	     		case R.id.info4:    
	     			AlertDialog.Builder alertDialog4 = new AlertDialog.Builder(FoodDiaryActivity.this)
	 				.setTitle("Meat")
	 				.setMessage("It is important to eat meat for a number of different reasons, most importantly, " +
	 						"it is rich source of proteins. \nProteins are essential for a number of different reasons included cell and muscle repair. " +
	 						"While proteins can be found in many other sources, they are most commonly found in the highest quantities and quality in meats. " +
	 						"Red meat is also a rich source of iron, a key component of the bloods abillity to carry oxygen and function at optimum levels")
	 				.setPositiveButton("got it", new DialogInterface.OnClickListener() {
	 							public void onClick(DialogInterface dialog,int id) {
	 								
	 							}
	 						  });
	 						alertDialog4.show();
	 						
	      		break;
	      		    
	     		case R.id.info5:    
	     			AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(FoodDiaryActivity.this)
	 				.setTitle("Grains")
	 				.setMessage("The energy comes from the carbohydrate part of grains. " +
	 						"Grain products, such as breads and pasta, provide a significant " +
	 						"supply of the energy our body needs\n Grain " +
	 						"products offer us even more. Grains supply essential vitamins and minerals, " +
	 						"including thiamine, riboflavin, niacin and folic acid, and iron. \n" +
	 						"Whole grain and whole wheat products are also an important source of fibre in our diets.")
	 						.setPositiveButton("got it", new DialogInterface.OnClickListener() {
	 							public void onClick(DialogInterface dialog,int id) {
	 								
	 							}
	 						  });
	 						alertDialog5.show();
	 						break;  		    
	 	    	  	}
	     		   
	 	      	}
	     	};
	  public void onItemSelected(AdapterView<?> parent,
	                                View v, int position, long id) {
		
		  	
		  	switch(parent.getId()) {
		  	case R.id.spinner: 
			selection.setText(items[position]);
			break;
		  	case R.id.spinner2: 
			selection2.setText(items[position]);
			break;
		  	case R.id.spinner3:
			selection3.setText(items[position]);
			break;
		  	case R.id.spinner4: 
			selection4.setText(items[position]);
			break;
		  	case R.id.spinner5: 
			selection5.setText(items[position]);
			break;
		  	
		  	
		  	 }
  		   
	 

		  
	}
	  
	  
	  public void onNothingSelected(AdapterView<?> parent) {
	    selection.setText("");
	    selection2.setText("");
	    selection3.setText("");
	    selection4.setText("");
	    selection5.setText("");
	  }
	}
	

		

	
