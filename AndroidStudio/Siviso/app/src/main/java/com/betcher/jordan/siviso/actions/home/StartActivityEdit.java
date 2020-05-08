package com.betcher.jordan.siviso.actions.home;

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
		myIntent.putExtra(Defaults.EXTRA_NAME_ID, selectedSiviso.getId());
		myIntent.putExtra(Defaults.EXTRA_NAME_NAME, selectedSiviso.getName());
		myIntent.putExtra(Defaults.EXTRA_NAME_SIVISO, selectedSiviso.getSiviso());
		myIntent.putExtra(Defaults.EXTRA_NAME_LATITUDE, selectedSiviso.getLatitude());
		myIntent.putExtra(Defaults.EXTRA_NAME_LONGITUDE, selectedSiviso.getLongitude());
		home.startActivity(myIntent);
	}
}
