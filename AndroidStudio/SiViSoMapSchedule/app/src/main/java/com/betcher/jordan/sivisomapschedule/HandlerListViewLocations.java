package com.betcher.jordan.sivisomapschedule;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

class HandlerListViewLocations implements AdapterView.OnItemClickListener
{
	Context        context;
	SQLiteLocation databaseLocation;
	ListView       listViewLocations;
	
	HashMap<Location, Integer> locationIds;
	ArrayList<Location>        locations;
	Location                   locationSelected;
	ListAdapterLocations       listAdapterLocations;
	View                       viewPrevious;
	
	int colorHighlight;
	
	public HandlerListViewLocations(Context context, SQLiteLocation databaseLocation, ListView listViewLocations)
	{
		this.context           = context;
		this.databaseLocation  = databaseLocation;
		this.listViewLocations = listViewLocations;
		
		colorHighlight = context.getResources().getColor(R.color.common_google_signin_btn_text_light_default);
		
		locationIds          = databaseLocation.getDatabaseAsArrayList();
		locations            = new ArrayList<Location>(locationIds.keySet());
		listAdapterLocations = new ListAdapterLocations(context, locations);
		
		listViewLocations.setAdapter(listAdapterLocations);
		listViewLocations.setOnItemClickListener(this);
	}
	
	public void refresh()
	{
		locationIds          = databaseLocation.getDatabaseAsArrayList();
		locations            = new ArrayList<>(locationIds.keySet());
		listAdapterLocations.clear();
		listAdapterLocations.addAll(locations);
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
		locationSelected = locations.get(position);
	}
	
	public Location getLocationSelected()
	{
		return locationSelected;
	}
	
	public Integer getLocationSelectedId()
	{
		return locationIds.get(locationSelected);
	}
}
