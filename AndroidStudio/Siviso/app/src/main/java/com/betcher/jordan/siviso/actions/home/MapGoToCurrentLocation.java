package com.betcher.jordan.siviso.actions.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.betcher.jordan.siviso.Defaults;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class MapGoToCurrentLocation
{
	@SuppressLint("MissingPermission")
	public static void run(Context context, GoogleMap map)
	{
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(Defaults.HOME_LATLNG, Defaults.HOME_ZOOM));
		
		LocationManager locationManager = (LocationManager) context.getApplicationContext().getSystemService(
				Context.LOCATION_SERVICE);
		LocationListener listener = new LocationListenerMapGoToCurrentLocation(map, locationManager);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
	}
	
	private static class LocationListenerMapGoToCurrentLocation implements LocationListener
	{
		GoogleMap map;
		LocationManager locationManager;
		
		public LocationListenerMapGoToCurrentLocation(GoogleMap map,
		                                              LocationManager locationManager)
		{
			this.map = map;
			this.locationManager = locationManager;
		}
		
		@Override
		public void onLocationChanged(Location currentLocation)
		{
			double latitude        = currentLocation.getLatitude();
			double longitude       = currentLocation.getLongitude();
			LatLng currentLatLng = new LatLng(latitude, longitude);
			this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, Defaults.HOME_ZOOM));
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
	}
}
