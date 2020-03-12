package com.betcher.jordan.sivisomapschedule.Locations;

import android.content.Context;
import android.widget.ListView;

import com.betcher.jordan.sivisomapschedule.SQLiteLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationsListViewWrapper
{
	SQLiteLocation databaseLocation;
	ListView       listViewLocations;
	
	HashMap<Location, Integer> locationIds;
	ArrayList<Location>        locations;
	LocationsListAdapter       locationsListAdapter;
	
	
	public LocationsListViewWrapper(Context context, SQLiteLocation databaseLocation, ListView listViewLocations)
	{
		this.databaseLocation  = databaseLocation;
		this.listViewLocations = listViewLocations;
		
		
		locationIds          = databaseLocation.getDatabaseAsArrayList();
		locations            = new ArrayList<Location>(locationIds.keySet());
		locationsListAdapter = new LocationsListAdapter(context, locations);
		
		listViewLocations.setAdapter(locationsListAdapter);
	}
	
	public void refresh()
	{
		locationIds          = databaseLocation.getDatabaseAsArrayList();
		locations            = new ArrayList<>(locationIds.keySet());
		locationsListAdapter.clear();
		locationsListAdapter.addAll(locations);
	}
	
	public int getId(Location location)
	{
		return locationIds.get(location);
	}
	
	public Location getLocation(int position)
	{
		return locations.get(position);
	}
}
