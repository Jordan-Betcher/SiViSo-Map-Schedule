package com.jordan.betcher.sivisoLite.activities.home.action;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.activities.Home;

public class ListenerCircleHandler implements GoogleMap.OnMapClickListener
{
	Home home;
	GoogleMap map;
	Circle circle = null;
	
	public ListenerCircleHandler(Home home, GoogleMap map)
	{
		this.home = home;
		this.map = map;
		
		if(PreferencesForSivisoLite.getHomeExists(home) == true)
		{
			createCircle(PreferencesForSivisoLite.getHomeLatLng(home));
		}
	}
	
	@Override
	public void onMapClick(LatLng latLng)
	{
		if(circle != null)
		{
			//circle.remove();
			circle.setCenter(latLng);
		}
		else
		{
			createCircle(latLng);
		}
	}
	
	private void createCircle(LatLng latLng)
	{
		PreferencesForSivisoLite.setHomeExists(home, true);
		PreferencesForSivisoLite.setHomeLatLng(home, latLng);
		
		
		CircleOptions circleOptions = new CircleOptions();
		circleOptions.center(latLng);
		circleOptions.radius(Defaults.HOME_RADIUS);
		circleOptions.fillColor(Defaults.SIVISO_TO_INTEGER.get(PreferencesForSivisoLite.getHomeSiviso(home)));
		circleOptions.strokeColor(Defaults.HOME_STROKE_COLOR);
		circleOptions.strokeWidth(Defaults.HOME_STROKE_WIDTH);
		
		circle = map.addCircle(circleOptions); //somehow get location listener access to circle
		// use circle.setStrokeColor(color);
	}
}
