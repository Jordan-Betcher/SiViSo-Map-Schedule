package com.betcher.jordan.sivisomapschedule.Locations;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;

import com.betcher.jordan.sivisomapschedule.R;

public class LocationsOnItemClickListenerSelect  implements AdapterView.OnItemClickListener
{
	View                       viewPrevious;
	int colorHighlight;
	Location                   locationSelected;
	LocationsListViewWrapper locationsListViewWrapper;
	
	public LocationsOnItemClickListenerSelect(Context context, LocationsListViewWrapper locationsListViewWrapper)
	{
		
		this.locationsListViewWrapper = locationsListViewWrapper;
		colorHighlight = context.getResources().getColor(R.color.common_google_signin_btn_text_light_default);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		if (viewPrevious != null)
		{
			viewPrevious.setBackgroundColor(Color.TRANSPARENT);
		}
		
		viewPrevious = view;
		view.setBackgroundColor(colorHighlight);
		locationSelected = locationsListViewWrapper.getLocation(position);
	}
	
	
	public Location getLocationSelected()
	{
		return locationSelected;
	}
	
	public Integer getLocationSelectedId()
	{
		return locationsListViewWrapper.getId(locationSelected);
	}
}
