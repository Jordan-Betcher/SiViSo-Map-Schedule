package com.jordan.betcher.siviso.livedatafrompreferences;

import com.google.android.gms.maps.model.LatLng;

public class Home
{
	Ringmode ringmode;
	LatLng latLng;
	
	public Home(Ringmode ringmode){this.ringmode = ringmode;}
	
	public Ringmode ringmode()
	{
		return ringmode;
	}
	
	public LatLng latLng()
	{
		return latLng;
	}
	
	public void setLatLng(int longitude, int latitude)
	{
		latLng = new LatLng(longitude, latitude);
	}
}
