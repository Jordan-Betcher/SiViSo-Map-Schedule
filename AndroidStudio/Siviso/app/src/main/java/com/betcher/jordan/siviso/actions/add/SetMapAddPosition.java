package com.betcher.jordan.siviso.actions.add;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.betcher.jordan.siviso.Defaults;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class SetMapAddPosition
{
	public static void run(AppCompatActivity activity, GoogleMap map)
	{
		Intent intent = activity.getIntent();
		Double latitude  = intent.getDoubleExtra("latitude", 0);
		Double longitude  = intent.getDoubleExtra("longitude", 0);
		LatLng previousActivityLatLng = new LatLng(latitude, longitude);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(previousActivityLatLng,
		                                                 Defaults.ADD_ZOOM));
	}
}
