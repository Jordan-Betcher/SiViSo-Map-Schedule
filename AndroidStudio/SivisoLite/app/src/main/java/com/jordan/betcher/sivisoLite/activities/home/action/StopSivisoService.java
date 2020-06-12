package com.jordan.betcher.sivisoLite.activities.home.action;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.SivisoService;

public class StopSivisoService
{
	public static void run(Context context)
	{
		context.stopService(new Intent(context, SivisoService.class));
		
		PreferencesForSivisoLite.setIsServiceRunning(context, false);
	}
}
