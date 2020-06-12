package com.jordan.betcher.sivisoLite.activities.home.action;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.jordan.betcher.sivisoLite.Defaults;

public class GoToCurrentLocation implements LocationListener
{
	LocationManager locationManager;
	GoogleMap map;
	
	public GoToCurrentLocation(LocationManager locationManager, GoogleMap map)
	{
		this.locationManager = locationManager;
		this.map = map;
	}
	
	public static void run(LocationManager locationManager, GoogleMap googleMap)
	{
		new GoToCurrentLocation(locationManager, googleMap)
				.goToCurrentLocation();
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
	public void onStatusChanged(String provider, int status, Bundle extras)
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
	
	@SuppressLint("MissingPermission")
	public void goToCurrentLocation()
	{
		locationManager.requestLocationUpdates
		(
			LocationManager.GPS_PROVIDER
			, Defaults.MAP_REQUEST_TIME_MIN
			, 0
			, this
		);
	}
}
