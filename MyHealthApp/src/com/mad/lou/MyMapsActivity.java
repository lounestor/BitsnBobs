package com.mad.lou;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

public class MyMapsActivity extends MapActivity {  
	private MapView map=null;  
	private MyLocationOverlay me=null;  
	private SitesOverlay sites=null;   
	@Override 
	public void onCreate(Bundle savedInstanceState) {    
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.maps_layout);        
		map=(MapView)findViewById(R.id.mapview);
		map.getController().setCenter(getPoint(52.668,  -8.625)); 
		    
		map.getController().setZoom(10);    
		map.setBuiltInZoomControls(true);        
		sites=new SitesOverlay();    
		map.getOverlays().add(sites);       
		me=new MyLocationOverlay(this, map);   
		map.getOverlays().add(me);  
		}    
	@Override  
	public void onResume() {    
		super.onResume();        
		me.enableCompass();  
		}     
	@Override  public void onPause() {    
		super.onPause();        
		me.disableCompass();  
		}      
	@Override  
	protected boolean isRouteDisplayed() {    
		return(false);  
		}    
	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event) {    
		if (keyCode == KeyEvent.KEYCODE_S) {      
			map.setSatellite(!map.isSatellite());      
			return(true);    
			}    
		else if (keyCode == KeyEvent.KEYCODE_Z) {      
			map.displayZoomControls(true);      
			return(true);    
			} 
		     
		return(super.onKeyDown(keyCode, event));  
		}  
	private GeoPoint getPoint(double lat, double lon) {    
		return(new GeoPoint((int)(lat*1000000.0),
				(int)(lon*1000000.0)));  
		}      
	private class SitesOverlay extends ItemizedOverlay<CustomItem> {    
		   
		private List<CustomItem> items=new ArrayList<CustomItem>();    
		private PopupPanel panel=new PopupPanel(R.layout.popup);        
		public SitesOverlay() {      
			super(null);            
			   
			items.add(new CustomItem(getPoint(52.6878, -8.4039),
					"", "Clare Glenns",
					getMarker(R.drawable.mapmarker)));      
			items.add(new CustomItem(getPoint(52.706,  -8.760),
					"Cratloe Woods",   "Cratloe ",                                
					getMarker(R.drawable.mapmarker)));      
			items.add(new CustomItem(getPoint(52.6745,  -8.5709),     
					"UL", "University of Limerick",
					getMarker(R.drawable.mapmarker)));      
			items.add(new CustomItem(getPoint(52.6854,  -8.6115), 
					"TShannon Banks", "Take a walk along the shannon Banks", 
					getMarker(R.drawable.mapmarker))); 
			items.add(new CustomItem(getPoint(52.6623,  -8.6267), 
					"Sage cafe", "Sage Cafe", 
					getMarker(R.drawable.food)));
			items.add(new CustomItem(getPoint(52.63,  -8.65), 
					"Delish", "Delish cafe", 
					getMarker(R.drawable.food)));
			populate();    
			}        
		@Override    
		protected CustomItem createItem(int i) {      
			return(items.get(i));   
			}       
		@Override    
		public void draw(Canvas canvas, MapView mapView,                      
				boolean shadow) {     
			super.draw(canvas, mapView, shadow);          
			}        
		@Override    
		protected boolean onTap(int i) {      
			OverlayItem item=getItem(i);      
			GeoPoint geo=item.getPoint();
			MapController mc = map.getController();	
			mc.animateTo(geo);
			mc.setZoom(15);
			View view=panel.getView();            
			((TextView)view.findViewById(R.id.latitude)).setText(String.valueOf(geo.getLatitudeE6()/1000000.0));      
			((TextView)view.findViewById(R.id.longitude)).setText(String.valueOf(geo.getLongitudeE6()/1000000.0));
			
			panel.show(true);           
			return(true);    
			}        
		@Override    
		public int size() {      
			return(items.size());    
			}        
		
		private Drawable getMarker(int resource) {      
			Drawable marker=getResources().getDrawable(resource);            
			marker.setBounds(0, 0, marker.getIntrinsicWidth(),marker.getIntrinsicHeight());      
			boundCenter(marker);      
			return(marker);    
			}  
		}    
	class PopupPanel {    
		View popup;    
		boolean isVisible=false;        
		PopupPanel(int layout) {      
			ViewGroup parent=(ViewGroup)map.getParent();      
			popup=getLayoutInflater().inflate(layout, parent, false);                        
			popup.setOnClickListener(new View.OnClickListener() {       
				public void onClick(View v) {         
					hide();        
					}      
				});    
			}        
		View getView() {      
			return(popup);   
			}        
		void show(boolean alignTop) {      
			RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(            
					RelativeLayout.LayoutParams.WRAP_CONTENT,            
					RelativeLayout.LayoutParams.WRAP_CONTENT      );            
			if (alignTop) {        
				lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				lp.setMargins(0, 20, 0, 0);      
			} else {        
				lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);        
				lp.setMargins(0, 0, 0, 60);     
			}
			hide();           
			((ViewGroup)map.getParent()).addView(popup, lp);      
			isVisible=true;    
			}        
		void hide() {      
			if (isVisible) {        
				isVisible=false;        
				((ViewGroup)popup.getParent()).removeView(popup);      
				}    
			}  
		}    
	class CustomItem extends OverlayItem {    
		Drawable marker=null;   
		CustomItem(GeoPoint pt, String name, String snippet,Drawable marker) {      
			super(pt, name, snippet);            
			this.marker=marker;      
			    
			}        
		@Override    
		public Drawable getMarker(int mark) {      
			Drawable result=(marker);            
			setState(result, mark);          
			return(result);    
			}        
		  
	}
} 








