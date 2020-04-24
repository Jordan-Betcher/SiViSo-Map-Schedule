package com.betcher.jordan.siviso.actions.home;

import com.betcher.jordan.siviso.Defaults;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

public class SetMapHomePosition
{
	public static void run(GoogleMap map)
	{
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(Defaults.HOME_LATLNG, Defaults.HOME_ZOOM));
	}
}
