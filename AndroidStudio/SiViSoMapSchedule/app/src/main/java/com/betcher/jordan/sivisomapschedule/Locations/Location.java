package com.betcher.jordan.sivisomapschedule.Locations;

import com.betcher.jordan.sivisomapschedule.SiViSo;
import com.google.android.gms.maps.model.LatLng;

public class Location
{
	private String name;
	private String latitude;
	private String longitude;
	private SiViSo siviso;
	
	public Location(String name, String latitude, String longitude, SiViSo siviso)
	{
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.siviso = siviso;
	}
	
	public Location(String name, LatLng latLng, SiViSo siviso)
	{
		this(name, latLng.latitude + "", latLng.longitude + "", siviso);
	}
	
	public String getName()
	{
		return name;
	}
	
	public SiViSo getSiviso()
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
