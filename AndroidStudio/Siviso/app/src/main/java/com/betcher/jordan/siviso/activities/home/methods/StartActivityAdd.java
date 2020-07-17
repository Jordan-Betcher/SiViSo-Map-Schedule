package com.betcher.jordan.siviso.activities.home.methods;

import android.content.Intent;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.Add;
import com.betcher.jordan.siviso.activities.Home;

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
