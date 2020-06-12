package com.jordan.betcher.sivisoLite.activities.home.action;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.SivisoService;

public class StartSivisoService
{
	public static void run(Context context)
	{
		Intent startSivisoService = new Intent(context, SivisoService.class);
		ContextCompat.startForegroundService(context, startSivisoService);
		
		PreferencesForSivisoLite.setIsServiceRunning(context, true);
	}
}
