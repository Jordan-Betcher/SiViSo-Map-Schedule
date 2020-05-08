package com.betcher.jordan.siviso.actions.home;

import android.content.Intent;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.Add;
import com.betcher.jordan.siviso.activities.Home;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class StartActivityAdd
{
	public static void run(Home home, double latitude, double longitude)
	{
		Intent myIntent = new Intent(home, Add.class);
		myIntent.putExtra(Defaults.EXTRA_NAME_LATITUDE, latitude);
		myIntent.putExtra(Defaults.EXTRA_NAME_LONGITUDE, longitude);
		home.startActivity(myIntent);
	}
}
