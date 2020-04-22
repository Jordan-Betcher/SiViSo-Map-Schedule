package com.betcher.jordan.sivisomapschedule.SivisoLocation;

import com.betcher.jordan.sivisomapschedule.Siviso;
import com.google.android.gms.maps.model.LatLng;

public class SivisoLocation
{
	private String name;
	private String latitude;
	private String longitude;
	private Siviso siviso;
	
	public SivisoLocation(String name, String latitude, String longitude, Siviso siviso)
	{
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.siviso = siviso;
	}
	
	public SivisoLocation(String name, LatLng latLng, Siviso siviso)
	{
		this(name, latLng.latitude + "", latLng.longitude + "", siviso);
	}
	
	public String getName()
	{
		return name;
	}
	
	public Siviso getSiviso()
	{
		return siviso;
	}
	
	public LatLng getLatLng()
	{
		double lat = Double.valueOf(latitude);
		double lng = Double.valueOf(longitude);
		LatLng latLng = new LatLng(lat, lng);
		
		return latLng;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
}
