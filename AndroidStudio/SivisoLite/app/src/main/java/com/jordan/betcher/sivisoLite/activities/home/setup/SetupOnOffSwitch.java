package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.activities.home.action.StartSivisoService;

public class SetupOnOffSwitch
{
	public static void run(Context context, Switch switchOnOff)
	{
		boolean isServiceRunning = PreferencesForSivisoLite.getIsServiceRunning(context);
		
		if(isServiceRunning)
		{
			StartSivisoService.run(context);
			switchOnOff.setChecked(true);
		}
	}
}
