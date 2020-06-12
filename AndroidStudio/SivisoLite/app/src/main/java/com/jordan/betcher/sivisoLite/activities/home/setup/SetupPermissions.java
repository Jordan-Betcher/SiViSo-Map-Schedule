package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.activities.Home;

public class SetupPermissions
{
	public static void run(Home home)
	{
		
		permissionFineLocation(home);
	}
	
	private static void permissionFineLocation(Home home)
	{
		if
		(
			ActivityCompat.checkSelfPermission(home, Manifest.permission.ACCESS_FINE_LOCATION)
			!= PackageManager.PERMISSION_GRANTED
		    && ActivityCompat.checkSelfPermission(home, Manifest.permission.ACCESS_COARSE_LOCATION)
	        != PackageManager.PERMISSION_GRANTED
		)
		{
			ActivityCompat
			.requestPermissions
			(
				home,
				new String[]
				{
					Manifest.permission.ACCESS_FINE_LOCATION
				},
				Defaults.REQUEST_LOCATION_PERMISSION
			);
		}
	}
}
