package com.betcher.jordan.siviso.actions.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.service.Siviso;

public class StartSivisoService
{
	private static final String TAG = "StartSivisoService";
	
	public static void run(Context context)
	{
		Log.d(TAG, "run: Starting Siviso Service");
		Intent startSivisoService = new Intent(context, Siviso.class);
		ContextCompat.startForegroundService(context, startSivisoService);
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean("isServiceRunning", true).apply();
	}
}
