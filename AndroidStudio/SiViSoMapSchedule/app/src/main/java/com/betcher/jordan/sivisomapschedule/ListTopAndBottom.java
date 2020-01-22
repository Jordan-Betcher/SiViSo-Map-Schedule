package com.betcher.jordan.sivisomapschedule;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

class ListTopAndBottom
{
	
	private int idListTop;
	private int idListBottom;
	
	public List<ListItemLocationSiViSo> listTop    = new ArrayList<ListItemLocationSiViSo>();
	public List<ListItemLocationSiViSo> listBottom = new ArrayList<ListItemLocationSiViSo>();
	
	public ListView listViewTop;
	public ListView listViewBottom;
	
	public LocationSiViSoListAdapter adapterTop;
	public LocationSiViSoListAdapter adapterBottom;
	
	public ListTopAndBottom(Activity activity, int idLayoutOfItem, int idListTop, int idListBottom)
	{
		this.idListTop    = idListTop;
		this.idListBottom = idListBottom;
		
		listViewTop    = (ListView) activity.findViewById(idListTop);
		listViewBottom = (ListView) activity.findViewById(idListBottom);
		
		adapterTop    = new LocationSiViSoListAdapter(activity, idLayoutOfItem, listTop);
		adapterBottom = new LocationSiViSoListAdapter(activity, idLayoutOfItem, listBottom);
		
		listViewTop.setAdapter(adapterTop);
		listViewBottom.setAdapter(adapterBottom);
	}
	
	
	public int getIdListTop()
	{
		return idListTop;
	}
	
	public int getIdListBottom()
	{
		return idListBottom;
	}
}
