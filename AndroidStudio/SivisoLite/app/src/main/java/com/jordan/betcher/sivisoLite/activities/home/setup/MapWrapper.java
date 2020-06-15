package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
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
import com.jordan.betcher.sivisoLite.activities.home.action.ListenerCircleHandler;

import java.util.Map;

public class MapWrapper
{
	private Home home;
	private GoogleMap map = null;
	private LatLng currentLocation = null;
	
	public MapWrapper(Home home)
	{
		this.home = home;
		setupMap();
	}
	
	public void goToCurrentLocation()
	{
		if(currentLocation == null)
		{
			addCurrentLocationListener();
		}
		else
		{
			this
			.map
			.moveCamera
			(
			CameraUpdateFactory
			.newLatLng(currentLocation)
			);
		}
	}
	
	public void goToHomeLocation()
	{
		LatLng homeLatLng = PreferencesForSivisoLite
		.getHomeLatLng(home);
		
		this
		.map
		.moveCamera
		(
		CameraUpdateFactory.newLatLng(homeLatLng)
		);
	}
	
	public void setupMap()
	{
		SupportMapFragment mapFragment =
		(SupportMapFragment)
		home
		.getSupportFragmentManager()
		.findFragmentById(R.id.homeMap);
		
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			
			@SuppressLint("MissingPermission")
			@Override
			public void onMapReady(GoogleMap map)
			{
				MapWrapper.this.map = map;
				map.setMyLocationEnabled(true);
				
				LocationManager
				locationManager
				= (LocationManager) home
				.getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);
				
				locationManager.requestLocationUpdates
				(
				LocationManager.GPS_PROVIDER
				, Defaults.MAP_REQUEST_TIME_MIN
				, 0
				,
				new GoToCurrentLocationListener(locationManager, map)
				);
				map.setOnMapClickListener(
				new ListenerCircleHandler(home, map));
				addCurrentLocationListener();
			}
		});
	}
	
	@SuppressLint("MissingPermission")
	public void addCurrentLocationListener()
	{
		LocationManager
		locationManager
		= (LocationManager) home
		.getApplicationContext()
		.getSystemService(Context.LOCATION_SERVICE);
		
		locationManager
		.requestLocationUpdates
		(
		LocationManager.GPS_PROVIDER
		, Defaults.MAP_REQUEST_TIME_MIN
		, 10
		, new TrackCurrentLocation()
		);
	}
	
	private class GoToCurrentLocationListener
	implements LocationListener
	{
		LocationManager locationManager;
		GoogleMap map;
		
		public GoToCurrentLocationListener(
		LocationManager locationManager, GoogleMap map)
		{
			this.locationManager = locationManager;
			this.map = map;
		}
		
		@Override
		public void onLocationChanged(Location currentLocation)
		{
			double latitude = currentLocation.getLatitude();
			double longitude = currentLocation.getLongitude();
			LatLng currentLatLng = new LatLng(latitude, longitude);
			
			this
			.map
			.moveCamera
			(
			CameraUpdateFactory
			.newLatLngZoom
			(
			currentLatLng
			, Defaults.MAP_ZOOM
			)
			);
			
			locationManager.removeUpdates(this);
		}
		
		@Override
		public void onStatusChanged(
		String provider, int status, Bundle extras)
		{
		
		}
		
		@Override
		public void onProviderEnabled(String provider)
		{
		
		}
		
		@Override
		public void onProviderDisabled(String provider)
		{
		
		}
	}
	
	private class TrackCurrentLocation implements LocationListener
	{
		
		@Override
		public void onLocationChanged(Location location)
		{
			LatLng currentLatLng = new LatLng(location.getLatitude(),
			                                  location
			                                  .getLongitude());
			MapWrapper.this.currentLocation = currentLatLng;
		}
		
		@Override
		public void onStatusChanged(
		String provider, int status, Bundle extras)
		{
		
		}
		
		@Override
		public void onProviderEnabled(String provider)
		{
		
		}
		
		@Override
		public void onProviderDisabled(String provider)
		{
		
		}
	}
}
