package com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso;

import com.betcher.jordan.siviso.database.SivisoData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;

public class TriggerSelectItem implements GoogleMap.OnCircleClickListener
{
	private static final String TAG = "TriggerSelectItem";
	com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemClickListener.SelectItem selectItem;
	SivisoMapCircles sivisoMapCircles;
	
	public TriggerSelectItem(
	com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemClickListener.SelectItem selectItem,
	SivisoMapCircles sivisoMapCircles)
	{
		this.selectItem = selectItem;
		this.sivisoMapCircles = sivisoMapCircles;
	}
	
	@Override
	public void onCircleClick(Circle circle)
	{
		SivisoData selectedSiviso = sivisoMapCircles.getSiviso(circle);
		selectItem.notifySelect(selectedSiviso);
	}
}
