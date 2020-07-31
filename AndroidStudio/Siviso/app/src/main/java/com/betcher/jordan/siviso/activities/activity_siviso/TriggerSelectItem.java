package com.betcher.jordan.siviso.activities.activity_siviso;

import com.betcher.jordan.siviso.database.TableRow_Siviso;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;

public class TriggerSelectItem implements GoogleMap.OnCircleClickListener
{
	private static final String TAG = "TriggerSelectItem";
	com.betcher.jordan.siviso.activities.activity_siviso.onItemClickListener.SelectItem selectItem;
	SivisoMapCircles sivisoMapCircles;
	
	public TriggerSelectItem(
	com.betcher.jordan.siviso.activities.activity_siviso.onItemClickListener.SelectItem selectItem,
	SivisoMapCircles sivisoMapCircles)
	{
		this.selectItem = selectItem;
		this.sivisoMapCircles = sivisoMapCircles;
	}
	
	@Override
	public void onCircleClick(Circle circle)
	{
		TableRow_Siviso selectedSiviso = sivisoMapCircles.getSiviso(circle);
		selectItem.notifySelect(selectedSiviso);
	}
}
