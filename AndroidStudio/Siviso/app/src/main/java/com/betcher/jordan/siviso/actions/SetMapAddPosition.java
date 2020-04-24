package com.betcher.jordan.siviso.actions;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.betcher.jordan.siviso.Constants;
import com.betcher.jordan.siviso.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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
		                                                 Constants.DEFAULT_ADD_ZOOM));
	}
}
