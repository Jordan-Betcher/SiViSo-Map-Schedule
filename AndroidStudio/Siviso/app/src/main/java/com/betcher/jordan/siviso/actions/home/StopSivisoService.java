package com.betcher.jordan.siviso.actions.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.service.Siviso;

public class StopSivisoService
{
	private static final String TAG = "StopSivisoService";
	public static void run(Context context)
	{
		Log.d(TAG, "run: Stopping Siviso Service");
		context.stopService(new Intent(context, Siviso.class));
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean("isServiceRunning", false).apply();
	}
}
