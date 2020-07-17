package com.betcher.jordan.siviso.activities.home.methods;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.service.Service_ManageRingMode;

public class StopSivisoService
{
	private static final String TAG = "StopSivisoService";
	public static void run(Context context)
	{
		Log.d(TAG, "run: Stopping Service_ManageRingMode Service");
		context.stopService(new Intent(context, Service_ManageRingMode.class));
		
		Preferences_Siviso.saveIsServiceRunning(context, false);
	}
}
