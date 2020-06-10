package com.jordan.betcher.sivisoLite.activities.home.action;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jordan.betcher.sivisoLite.activities.Defaults;
import com.jordan.betcher.sivisoLite.activities.service.SivisoService;

public class StopSivisoService
{
	public static void run(Context context)
	{
		context.stopService(new Intent(context, SivisoService.class));
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(Defaults.PREFERENCE_KEY_IS_SERVICE_RUNNING, false).apply();
	}
}
