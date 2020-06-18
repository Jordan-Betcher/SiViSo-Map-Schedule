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
			createOnlyOneCircle(PreferencesForSivisoLite.getHomeLatLng(home));
		}
	}
	
	@Override
	public void onMapClick(LatLng latLng)
	{
		createOnlyOneCircle(latLng);
	}
	
	private void createOnlyOneCircle(LatLng latLng)
	{
		PreferencesForSivisoLite.setHomeExists(home, true);
		PreferencesForSivisoLite.setHomeLatLng(home, latLng);
		
		if(circle != null)
		{
			circle.remove();
		}
		
		CircleOptions circleOptions = new CircleOptions();
		circleOptions.center(latLng);
		circleOptions.radius(Defaults.HOME_RADIUS);
		circleOptions.fillColor(Defaults.HOME_FILL_COLOR);
		circleOptions.strokeColor(Defaults.HOME_STROKE_COLOR);
		circleOptions.strokeWidth(Defaults.HOME_STROKE_WIDTH);
		
		circle = map.addCircle(circleOptions);
	}
}
