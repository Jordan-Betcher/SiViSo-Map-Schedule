package com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener;

import com.betcher.jordan.siviso.database.TableRow_Siviso;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

public class ZoomToSelect implements OnItemSelectListener
{
	GoogleMap map;
	
	public ZoomToSelect(GoogleMap map)
	{
		this.map = map;
	}
	
	@Override
	public void onItemSelect(
	TableRow_Siviso selectedSivisoData)
	{
		map.moveCamera(CameraUpdateFactory.newLatLng(selectedSivisoData.latLng()));
	}
	
	@Override
	public void onItemDeselect()
	{
	
	}
}
