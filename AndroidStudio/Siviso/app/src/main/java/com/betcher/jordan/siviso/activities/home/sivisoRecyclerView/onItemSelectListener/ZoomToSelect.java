package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener;

import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemSelectListener;
import com.betcher.jordan.siviso.database.SivisoData;
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
	public void onItemSelect(SivisoData selectedSivisoData)
	{
		map.moveCamera(CameraUpdateFactory.newLatLng(selectedSivisoData.latLng()));
	}
	
	@Override
	public void onItemDeselect()
	{
	
	}
}
