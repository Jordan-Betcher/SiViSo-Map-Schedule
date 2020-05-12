package com.betcher.jordan.siviso.actions.home;

import android.view.View;
import android.widget.AdapterView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.home.sivisoMapCircles.SivisoMapCircles;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.OnItemSelectedListener;
import com.betcher.jordan.siviso.database.SivisoData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

public class ZoomToSelected implements OnItemSelectedListener
{
	GoogleMap map;
	
	public ZoomToSelected(GoogleMap map)
	{
		this.map = map;
	}
	
	@Override
	public void onItemSelect(SivisoData selectedSivisoData)
	{
		map.moveCamera(CameraUpdateFactory.newLatLng(selectedSivisoData.getLatLng()));
	}
}
