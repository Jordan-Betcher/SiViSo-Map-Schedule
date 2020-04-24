package com.betcher.jordan.siviso.actions;

import android.content.Intent;

import com.betcher.jordan.siviso.activities.Add;
import com.betcher.jordan.siviso.activities.Home;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class StartActivityAdd
{
	public static void run(Home home, LatLng mapPosition)
	{
		Intent myIntent = new Intent(home, Add.class);
		myIntent.putExtra("latitude", mapPosition.latitude);
		myIntent.putExtra("longitude", mapPosition.longitude);
		home.startActivity(myIntent);
	}
}
