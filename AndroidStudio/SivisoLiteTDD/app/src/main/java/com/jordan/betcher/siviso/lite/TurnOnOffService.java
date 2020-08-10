package com.jordan.betcher.siviso.lite;

import android.view.View;

class TurnOnOffService implements View.OnClickListener
{
	RunStop service;
	
	public TurnOnOffService(RunStop service)
	{
		this.service = service;
	}
	
	@Override
	public void onClick(View v)
	{
		if(isServiceRunning())
		{
			service.stop();
		}
		else
		{
			service.run();
		}
	}
	
	private boolean isServiceRunning()
	{
		return service.running();
	}
}
