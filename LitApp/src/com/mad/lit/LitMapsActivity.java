package com.mad.lit;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.mad.lit.CustomItemizedOverlay;


public class LitMapsActivity extends MapActivity {
	
	private ViewFlipper mViewFlipper;
	private MapView mapView;
	private MapController control;
	private double latitude = 0.0;
	private double longitude = 0.0;
	private LocationManager manager;
	private LocationListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		
		mViewFlipper = (ViewFlipper) findViewById(R.id.mapCampusViewFlipper);
		
		ListView campusList = (ListView) findViewById(R.id.campusList);
		
		mapView = (MapView)findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
        
        control = mapView.getController();
        
        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        listener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				showToast("Gps enabled");
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				showToast("Gps disabled");
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				
			}
		};
		
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
		
		String[] campuses = getResources().getStringArray(R.array.campus);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mapitems, R.id.campusName, campuses);
		campusList.setAdapter(adapter);
		
		campusList.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> parent, View view,
					  int position, long id) {
				  
				  TextView tvSelectedCampus = (TextView)view.findViewById(R.id.campusName);
	  			  String campusName = tvSelectedCampus.getText().toString();
				  
				  showMapScreen(position, campusName);
			  } 
		}); 
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public void onBackPressed() {
		if(mViewFlipper.getDisplayedChild() == 1) {
			mViewFlipper.setDisplayedChild(0);
		}
		else {
			super.onBackPressed();
		}
    }
	
	private void showMapScreen(int campusId, String campusName) {
		
		mViewFlipper.setInAnimation(LitMapsActivity.this, R.anim.view_transition_in_right);
		mViewFlipper.setOutAnimation(LitMapsActivity.this, R.anim.view_transition_out_left);
		mViewFlipper.setDisplayedChild(1);
		
		if(campusId == 0) {
			latitude = 52.675029;
			longitude = -8.648474;
			
   		
    	}
    	else if(campusId == 1) {
    		latitude = 52.664919;
			longitude = -8.613841;
    	}
    	
		
		animateToLocation(campusName);
	}
	
	public void animateToLocation(String campusName) {
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable campusLocationMarker = this.getResources().getDrawable(R.drawable.marker);
		CustomItemizedOverlay campusLocationItemizedOverlay = new CustomItemizedOverlay(campusLocationMarker, this);
		
		GeoPoint point = new GeoPoint((int)(latitude * 1E6), (int)(longitude * 1E6));
		control.animateTo(point);
		
		OverlayItem campusItem = new OverlayItem(point, campusName + " Campus", null);
		campusLocationItemizedOverlay.addOverlay(campusItem);
		mapOverlays.clear();
	    mapOverlays.add(campusLocationItemizedOverlay);
	}
	
	public void animateToMyLocation(Location location) {
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable myLocationMarker = this.getResources().getDrawable(R.drawable.marker);
		CustomItemizedOverlay myLocationItemizedOverlay = new CustomItemizedOverlay(myLocationMarker, this);
		
		GeoPoint point = new GeoPoint((int)(location.getLatitude() * 1E6), 
                (int)(location.getLongitude() * 1E6));
		control.animateTo(point);
		
		OverlayItem item = new OverlayItem(point, "You are here", null);
		myLocationItemizedOverlay.addOverlay(item);
	    mapOverlays.add(myLocationItemizedOverlay);
	}
	
	private void showToast(String msg){
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
    	case R.id.locationMyLocation:
    		animateToMyLocation(manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
    		
    		return true;
    		
    	case R.id.locationMapView:
    		if(mapView.isSatellite()==true){
    			mapView.setSatellite(false);
    		}
    		return true;
    		
    	case R.id.locationSatelliteView:
    		if(mapView.isSatellite()==false){
    			mapView.setSatellite(true);
    		}
    		return true;
    		
    	default:
    		return super.onOptionsItemSelected(item);
	}
	}
	
}
