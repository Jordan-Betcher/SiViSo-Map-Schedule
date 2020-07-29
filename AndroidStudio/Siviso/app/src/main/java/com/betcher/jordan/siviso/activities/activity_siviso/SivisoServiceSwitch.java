package com.betcher.jordan.siviso.activities.activity_siviso;

import android.content.Context;
import android.content.Intent;
import android.widget.Switch;

import androidx.core.content.ContextCompat;

import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.service.Service_ManageRingMode;

class SivisoServiceSwitch
{
	
	Context context;
	Switch onOffSwitch;
	
	public SivisoServiceSwitch(Context context, Switch onOffSwitch)
	{
		this.context = context;
		this.onOffSwitch = onOffSwitch;
		setupOnOffSwitch();
	}
	
	private void setupOnOffSwitch()
	{
		boolean isServiceRunning = Preferences_Siviso
		.isServiceRunning(context);
		
		if(isServiceRunning)
		{
			startSivisoService();
			onOffSwitch.setChecked(true);
		}
	}
	
	public void refresh()
	{
		
		if (isSwitchOn())
		{
			startSivisoService();
		}
		else
		{
			stopSivisoService();
		}
	}
	
	private boolean isSwitchOn()
	{
		return onOffSwitch.isChecked();
	}
	
	private void startSivisoService()
	{
		Intent startSivisoService = new Intent(context,
		                                       Service_ManageRingMode.class);
		ContextCompat
		.startForegroundService(context, startSivisoService);
		
		Preferences_Siviso.saveIsServiceRunning(context, true);
	}
	
	private void stopSivisoService()
	{
		context.stopService(new Intent(context, Service_ManageRingMode.class));
		Preferences_Siviso.saveIsServiceRunning(context, false);
	}
}
