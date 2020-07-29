package com.betcher.jordan.siviso.activities.activity_siviso;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.betcher.jordan.siviso.Defaults;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class LocationListenerMapGoToCurrentLocation implements LocationListener
{
	LocationManager locationManager;
	GoogleMap map;
	
	public LocationListenerMapGoToCurrentLocation(LocationManager locationManager, GoogleMap map)
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
		this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
		                                                      Defaults.HOME_ZOOM));
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
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, this);
	}
}
