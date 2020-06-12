package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.activities.home.action.GoToCurrentLocation;
import com.jordan.betcher.sivisoLite.activities.home.action.ListenerCircleHandler;

public class SetupHomeMap
{
	public static void run(Home home)
	{
		SupportMapFragment mapFragment =
				(SupportMapFragment)
						home
								.getSupportFragmentManager()
								.findFragmentById(R.id.homeMap);
		
		mapFragment.getMapAsync(new Setup(home));
	}
	
	private static class Setup implements OnMapReadyCallback
	{
		Home home;
		public Setup(Home home)
		{
			this.home = home;
		}
		
		@SuppressLint("MissingPermission")
		@Override
		public void onMapReady(GoogleMap map)
		{
			map.setMyLocationEnabled(true);
			
			LocationManager
				locationManager
				= (LocationManager) home
				.getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);
			
			GoToCurrentLocation.run(locationManager, map);
			
			map.setOnMapClickListener(new ListenerCircleHandler(home, map));
			
			/*
			if(PreferencesForSivisoLite.getHomeExists(home) == true)
			{
				Circle circle = map.addCircle(new CircleOptions().center(PreferencesForSivisoLite.getHomeLatLng(home))
				                                       .radius(Defaults.SIVISO_RADIUS)
				                                       .fillColor(Defaults.SIVISO_FILL_COLOR)
				                                       .strokeColor(Defaults.SIVISO_STROKE_COLOR)
				                                       .strokeWidth(Defaults.SIVISO_STROKE_WIDTH));
				circle.setClickable(true);
			}//*/
		}
	}
}
