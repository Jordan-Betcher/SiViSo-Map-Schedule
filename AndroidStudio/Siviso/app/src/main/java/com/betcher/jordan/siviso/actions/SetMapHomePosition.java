package com.betcher.jordan.siviso.actions;

import com.betcher.jordan.siviso.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

public class SetMapHomePosition
{
	public static void run(GoogleMap map)
	{
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.DEFAULT_HOME_LATLNG, Constants.DEFAULT_HOME_ZOOM));
	}
}
