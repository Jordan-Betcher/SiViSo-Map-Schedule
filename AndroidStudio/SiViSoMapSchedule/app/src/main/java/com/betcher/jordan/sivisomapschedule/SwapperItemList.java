package com.betcher.jordan.sivisomapschedule;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

class SwapperItemList
{
	private ListTopAndBottom listTopAndBottom;
	
	private ArrayList<ListItemLocationSiViSo> list = new ArrayList<ListItemLocationSiViSo>();
	
	private ListItemLocationSiViSo focused;
	
	private SupportMapFragment map;
	
	public SwapperItemList(Activity activity, int idLayoutOfItem, int idListTop, int idListBottom, SupportMapFragment mapFragment)
	{
		listTopAndBottom = new ListTopAndBottom(activity, idLayoutOfItem, idListTop, idListBottom);
		
		this.map = mapFragment;
		
		listTopAndBottom.listViewTop.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				if (focused == list.get(position))
				{
					focused = null;
				}
				else
				{
					focused = list.get(position);
				}
				
				updateTopAndBottomList();
			}
		});
		
		listTopAndBottom.listViewBottom.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				focused = list.get(listTopAndBottom.listTop.size() + position);
				updateTopAndBottomList();
			}
		});
		
	}
	
	public void AddListItem(ListItemLocationSiViSo listItem)
	{
		list.add(listItem);
		updateTopAndBottomList();
	}
	
	private void updateTopAndBottomList()
	{
		ListItemLocationSiViSo locationTest = new ListItemLocationSiViSo("location test", "silent");
		
		if (focused == null)
		{
			listTopAndBottom.listTop.removeAll(list);
			listTopAndBottom.listBottom.removeAll(list);
			
			listTopAndBottom.listTop.addAll(list);
			
			listTopAndBottom.adapterTop.notifyDataSetChanged();
			listTopAndBottom.adapterBottom.notifyDataSetChanged();
			
			map.getView().setVisibility(View.GONE);
		}
		else
		{
			int indexOfFocused = list.indexOf(focused);
			
			listTopAndBottom.listTop.removeAll(list);
			listTopAndBottom.listBottom.removeAll(list);
			
			//The "+ 1" is so that the focused is in listTop
			listTopAndBottom.listTop.addAll(list.subList(0, indexOfFocused + 1));
			listTopAndBottom.listBottom.addAll(list.subList(indexOfFocused + 1, list.size()));
			
			listTopAndBottom.adapterTop.notifyDataSetChanged();
			listTopAndBottom.adapterBottom.notifyDataSetChanged();
			
			map.getView().setVisibility(View.VISIBLE);
		}
	}
}
