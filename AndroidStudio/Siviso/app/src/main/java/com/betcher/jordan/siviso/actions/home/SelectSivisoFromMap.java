package com.betcher.jordan.siviso.actions.home;

import android.media.audiofx.DynamicsProcessing;
import android.util.Log;

import com.betcher.jordan.siviso.activities.home.sivisoMapCircles.SivisoMapCircles;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.database.SivisoData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;

public class SelectSivisoFromMap implements GoogleMap.OnCircleClickListener
{
	private static final String TAG = "SelectSivisoFromMap";
	SelectItem selectItem;
	SivisoMapCircles sivisoMapCircles;
	
	public SelectSivisoFromMap(SelectItem selectItem, SivisoMapCircles sivisoMapCircles)
	{
		this.selectItem = selectItem;
		this.sivisoMapCircles = sivisoMapCircles;
	}
	
	@Override
	public void onCircleClick(Circle circle)
	{
		Log.d(TAG, "onCircleClick: ");
		SivisoData selectedSiviso = sivisoMapCircles.getSiviso(circle);
		selectItem.callAllOnSelectItemListeners(selectedSiviso);
	}
}
