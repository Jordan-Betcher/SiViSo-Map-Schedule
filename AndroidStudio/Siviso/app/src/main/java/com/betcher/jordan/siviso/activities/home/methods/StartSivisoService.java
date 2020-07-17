package com.betcher.jordan.siviso.activities.home.methods;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.service.Service_ManageRingMode;

public class StartSivisoService
{
	private static final String TAG = "StartSivisoService";
	
	public static void run(Context context)
	{
		Log.d(TAG, "run: Starting Service_ManageRingMode Service");
		Intent startSivisoService = new Intent(context, Service_ManageRingMode.class);
		ContextCompat.startForegroundService(context, startSivisoService);
		
		Preferences_Siviso.saveIsServiceRunning(context, true);
	}
}
