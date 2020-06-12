package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.activities.home.action.GoToCurrentLocation;

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
		public void onMapReady(GoogleMap googleMap)
		{
			googleMap.setMyLocationEnabled(true);
			
			LocationManager
				locationManager
				= (LocationManager) home
				.getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);
			
			GoToCurrentLocation.run(locationManager, googleMap);
			
			
		}
	}
}
