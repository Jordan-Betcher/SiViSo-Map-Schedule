package com.jordan.betcher.sivisoLite.activities.home;

import android.content.Intent;

import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.activities.Settings;

public class OpenSettingsActivity
{
	public static void run(Home home)
	{
		
		Intent settingsIntent = new Intent(home, Settings.class);
		home.startActivity(settingsIntent);
	}
}
