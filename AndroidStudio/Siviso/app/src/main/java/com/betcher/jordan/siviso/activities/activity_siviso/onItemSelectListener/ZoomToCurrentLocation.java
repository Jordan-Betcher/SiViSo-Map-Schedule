package com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener;

import com.betcher.jordan.siviso.activities.activity_siviso.LocationListenerMapGoToCurrentLocation;
import com.betcher.jordan.siviso.database.DatabaseFormatted_Siviso;

public class ZoomToCurrentLocation implements OnItemSelectListener
{
	LocationListenerMapGoToCurrentLocation locationListenerMapGoToCurrentLocation;
	public ZoomToCurrentLocation(LocationListenerMapGoToCurrentLocation locationListenerMapGoToCurrentLocation)
	{
		this.locationListenerMapGoToCurrentLocation = locationListenerMapGoToCurrentLocation;
	}
	
	@Override
	public void onItemSelect(
	DatabaseFormatted_Siviso selectedSivisoData)
	{
		locationListenerMapGoToCurrentLocation.goToCurrentLocation();
	}
	
	@Override
	public void onItemDeselect()
	{
		//Do Nothing
	}
}
