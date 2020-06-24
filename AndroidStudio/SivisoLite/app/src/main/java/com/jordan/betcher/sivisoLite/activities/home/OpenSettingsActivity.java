package com.jordan.betcher.sivisoLite.activities.home;

import android.content.Intent;

import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.activities.SettingsActivity;

public class OpenSettingsActivity
{
	public static void run(Home home)
	{
		
		Intent settingsIntent = new Intent(home, SettingsActivity.class);
		home.startActivity(settingsIntent);
	}
}
