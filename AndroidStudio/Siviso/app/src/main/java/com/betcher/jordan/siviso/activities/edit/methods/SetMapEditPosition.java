package com.betcher.jordan.siviso.activities.edit.methods;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onMapCircleClickListener.SelectSivisoOnMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class SetMapEditPosition
{
	public static void run(AppCompatActivity activity,
	                       GoogleMap map,
	                       SelectSivisoOnMap selectSivisoOnMap)
	{
		Intent intent = activity.getIntent();
		Double latitude  = intent.getDoubleExtra(Defaults.EXTRA_NAME_LATITUDE, 0);
		Double longitude  = intent.getDoubleExtra(Defaults.EXTRA_NAME_LONGITUDE, 0);
		LatLng selectedSivisoDataLatLng = new LatLng(latitude, longitude);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedSivisoDataLatLng,
		                                                 Defaults.SIVISO_ZOOM));
		
		selectSivisoOnMap.onMapClick(selectedSivisoDataLatLng);
	}
}
