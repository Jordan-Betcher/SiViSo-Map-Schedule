package com.betcher.jordan.sivisomapschedule;

import android.content.Context;
import android.widget.Button;

import com.betcher.jordan.sivisomapschedule.Locations.Location;
import com.betcher.jordan.sivisomapschedule.Locations.LocationSelectListener;

public class DeleteButtonLocationSelectListener implements LocationSelectListener
{
	private String nameOfDefaultLocation;
	private Button deleteButton;
	
	public DeleteButtonLocationSelectListener(Context context, Button deleteButton)
	{
		nameOfDefaultLocation = context.getResources().getString(R.string.name_for_default_location);
		this.deleteButton = deleteButton;
	}
	
	@Override
	public void OnLocationSelected(Location location)
	{
		if(isDefaultLocation(location))
		{
			deleteButton.setEnabled(false);
		}
		else
		{
			deleteButton.setEnabled(true);
		}
	}
	
	private boolean isDefaultLocation(Location location)
	{
		if(location.getName().equals(nameOfDefaultLocation))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
