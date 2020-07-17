package com.betcher.jordan.siviso.activities.home.methods;

import android.content.Intent;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.Edit;
import com.betcher.jordan.siviso.activities.Home;
import com.betcher.jordan.siviso.database.SivisoData;

public class StartActivityEdit
{
	public static void run(Home home, SivisoData selectedSiviso)
	{
		Intent myIntent = new Intent(home, Edit.class);
		myIntent.putExtra(Defaults.EXTRA_NAME_ID, selectedSiviso.id());
		myIntent.putExtra(Defaults.EXTRA_NAME_NAME, selectedSiviso.name());
		myIntent.putExtra(Defaults.EXTRA_NAME_SIVISO, selectedSiviso.siviso().name());
		myIntent.putExtra(Defaults.EXTRA_NAME_LATITUDE, selectedSiviso.latitude());
		myIntent.putExtra(Defaults.EXTRA_NAME_LONGITUDE, selectedSiviso.longitude());
		home.startActivity(myIntent);
	}
}
