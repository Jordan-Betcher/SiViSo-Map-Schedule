package com.betcher.jordan.sivisomapschedule;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationListenerCurrentLocation implements LocationListener
{
	MapsActivity mapsActivity;
	
	public LocationListenerCurrentLocation(MapsActivity mapsActivity)
	{
		this.mapsActivity = mapsActivity;
	}
	
	@Override
	public void onLocationChanged(Location location)
	{
		double latitude        = location.getLatitude();
		double longitude       = location.getLongitude();
		LatLng currentLocation = new LatLng(latitude, longitude);
		mapsActivity.googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
		mapsActivity.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));
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
}
