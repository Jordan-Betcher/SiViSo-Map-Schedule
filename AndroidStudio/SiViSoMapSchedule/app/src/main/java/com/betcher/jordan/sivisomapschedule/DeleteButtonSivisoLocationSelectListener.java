package com.betcher.jordan.sivisomapschedule;

import android.content.Context;
import android.widget.Button;

import com.betcher.jordan.sivisomapschedule.SivisoLocation.SivisoLocation;
import com.betcher.jordan.sivisomapschedule.SivisoLocation.SivisoLocationSelectListener;

public class DeleteButtonSivisoLocationSelectListener implements SivisoLocationSelectListener
{
	private String nameOfDefaultLocation;
	private Button deleteButton;
	
	public DeleteButtonSivisoLocationSelectListener(Context context, Button deleteButton)
	{
		nameOfDefaultLocation = context.getResources().getString(R.string.name_for_default_location);
		this.deleteButton = deleteButton;
	}
	
	@Override
	public void OnLocationSelected(SivisoLocation sivisoLocation)
	{
		if(isDefaultLocation(sivisoLocation))
		{
			deleteButton.setEnabled(false);
		}
		else
		{
			deleteButton.setEnabled(true);
		}
	}
	
	private boolean isDefaultLocation(SivisoLocation sivisoLocation)
	{
		if(sivisoLocation.getName().equals(nameOfDefaultLocation))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
