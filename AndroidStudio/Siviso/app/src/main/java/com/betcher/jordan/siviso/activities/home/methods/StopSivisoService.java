package com.betcher.jordan.siviso.activities.home.methods;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.service.Service_ManageRingMode;

public class StopSivisoService
{
	private static final String TAG = "StopSivisoService";
	public static void run(Context context)
	{
		Log.d(TAG, "run: Stopping Service_ManageRingMode Service");
		context.stopService(new Intent(context, Service_ManageRingMode.class));
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean("isServiceRunning", false).apply();
	}
}
