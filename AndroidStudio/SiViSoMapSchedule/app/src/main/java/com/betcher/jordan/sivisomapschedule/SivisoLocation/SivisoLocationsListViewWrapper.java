package com.betcher.jordan.sivisomapschedule.SivisoLocation;

import android.content.Context;
import android.widget.ListView;

import com.betcher.jordan.sivisomapschedule.SQLiteLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class SivisoLocationsListViewWrapper
{
	SQLiteLocation databaseLocation;
	ListView       listViewLocations;
	
	HashMap<SivisoLocation, Integer> locationIds;
	ArrayList<SivisoLocation>        sivisoLocations;
	SivisoLocationsListAdapter       sivisoLocationsListAdapter;
	
	
	public SivisoLocationsListViewWrapper(Context context, SQLiteLocation databaseLocation, ListView listViewLocations)
	{
		this.databaseLocation  = databaseLocation;
		this.listViewLocations = listViewLocations;
		
		
		locationIds                = databaseLocation.getDatabaseAsArrayList();
		sivisoLocations            = new ArrayList<SivisoLocation>(locationIds.keySet());
		sivisoLocationsListAdapter = new SivisoLocationsListAdapter(context, sivisoLocations);
		
		listViewLocations.setAdapter(sivisoLocationsListAdapter);
	}
	
	public void refresh()
	{
		locationIds     = databaseLocation.getDatabaseAsArrayList();
		sivisoLocations = new ArrayList<>(locationIds.keySet());
		sivisoLocationsListAdapter.clear();
		sivisoLocationsListAdapter.addAll(sivisoLocations);
	}
	
	public int getId(SivisoLocation sivisoLocation)
	{
		return locationIds.get(sivisoLocation);
	}
	
	public SivisoLocation getLocation(int position)
	{
		return sivisoLocations.get(position);
	}
}
