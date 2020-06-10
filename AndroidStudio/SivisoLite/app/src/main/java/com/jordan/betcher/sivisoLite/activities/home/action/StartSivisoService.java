package com.jordan.betcher.sivisoLite.activities.home.action;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.jordan.betcher.sivisoLite.activities.Defaults;
import com.jordan.betcher.sivisoLite.activities.service.SivisoService;

public class StartSivisoService
{
	public static void run(Context context)
	{
		Intent startSivisoService = new Intent(context, SivisoService.class);
		ContextCompat.startForegroundService(context, startSivisoService);
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean("isServiceRunning", true).apply();
	}
}
