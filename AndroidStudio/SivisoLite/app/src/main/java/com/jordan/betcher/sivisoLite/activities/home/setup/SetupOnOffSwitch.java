package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.activities.Defaults;
import com.jordan.betcher.sivisoLite.activities.home.action.StartSivisoService;

public class SetupOnOffSwitch
{
	public static void run(Context context, Switch switchOnOff)
	{
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs.getBoolean(Defaults.PREFERENCE_KEY_IS_SERVICE_RUNNING, false);
		
		if(isServiceRunning)
		{
			StartSivisoService.run(context);
			switchOnOff.setChecked(true);
		}
	}
}
