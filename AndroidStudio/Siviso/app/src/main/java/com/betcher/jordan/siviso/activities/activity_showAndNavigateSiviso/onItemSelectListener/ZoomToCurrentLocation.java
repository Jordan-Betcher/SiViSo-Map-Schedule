package com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemSelectListener;

import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.LocationListenerMapGoToCurrentLocation;
import com.betcher.jordan.siviso.database.SivisoData;

public class ZoomToCurrentLocation implements OnItemSelectListener
{
	LocationListenerMapGoToCurrentLocation locationListenerMapGoToCurrentLocation;
	public ZoomToCurrentLocation(LocationListenerMapGoToCurrentLocation locationListenerMapGoToCurrentLocation)
	{
		this.locationListenerMapGoToCurrentLocation = locationListenerMapGoToCurrentLocation;
	}
	
	@Override
	public void onItemSelect(SivisoData selectedSivisoData)
	{
		locationListenerMapGoToCurrentLocation.goToCurrentLocation();
	}
	
	@Override
	public void onItemDeselect()
	{
		//Do Nothing
	}
}
