package com.betcher.jordan.sivisomapschedule;

import android.graphics.Color;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

class OnMapClickListenerSivisoArea implements GoogleMap.OnMapClickListener
{
	GoogleMap googleMap;
	Button buttonAdd;
	
	LatLng selectedLatLng = null;
	
	Circle sivisoCircle = null;
	int radius = 70;
	float strokeWidth = 4f;
	int strokeColor = Color.RED;
	int fillColor =  Color.argb(70, 150, 50, 50);
	
	public OnMapClickListenerSivisoArea(GoogleMap googleMap, Button buttonAdd)
	{
		this.googleMap = googleMap;
		this.buttonAdd = buttonAdd;
		
		//ButtonAdd isn't enabled until a spot on the map is selected
		this.buttonAdd.setEnabled(false);
	}
	
	@Override
	public void onMapClick(LatLng latLng)
	{
		createSivisoCircle(latLng);
		
		selectedLatLng = latLng;
	}
	
	private void createSivisoCircle(LatLng latLng)
	{
		clearLastSivisoCircle();
		
		sivisoCircle = googleMap.addCircle(new CircleOptions().center(latLng)
		                                                      .radius(radius)
		                                                      .strokeColor(strokeColor)
		                                                      .fillColor(fillColor)
		                                                      .strokeWidth(strokeWidth)
		)
		;
		
		buttonAdd.setEnabled(true);
	}
	
	private void clearLastSivisoCircle()
	{
		if (sivisoCircle != null)
		{
			sivisoCircle.remove();
		}
	}
	
	public LatLng getSelectedLatLng()
	{
		return selectedLatLng;
	}
}
