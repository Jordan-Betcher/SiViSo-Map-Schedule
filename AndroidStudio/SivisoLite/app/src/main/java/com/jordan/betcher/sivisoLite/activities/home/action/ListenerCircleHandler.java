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
	}
	
	@Override
	public void onMapClick(LatLng latLng)
	{
		PreferencesForSivisoLite.setHomeExists(home, true);
		PreferencesForSivisoLite.setHomeLatLng(home, latLng);
		
		CircleOptions circleOptions = new CircleOptions();
		circleOptions.center(latLng);
		circleOptions.radius(Defaults.SIVISO_RADIUS);
		circleOptions.fillColor(Defaults.SIVISO_FILL_COLOR);
		circleOptions.strokeColor(Defaults.SIVISO_STROKE_COLOR);
		circleOptions.strokeWidth(Defaults.SIVISO_STROKE_WIDTH);
		
		circle = map.addCircle(circleOptions);
	}
}
