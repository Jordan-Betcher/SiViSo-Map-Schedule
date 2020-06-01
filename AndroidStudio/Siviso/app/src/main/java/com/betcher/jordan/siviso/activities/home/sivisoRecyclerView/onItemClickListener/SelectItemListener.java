package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemSelectListener;
import com.betcher.jordan.siviso.database.SivisoData;

import java.util.ArrayList;

public class SelectItemListener
{
	
	
	ArrayList<OnItemSelectListener> onItemSelectListeners = new ArrayList<>();
	
	public void notifySelect(SivisoData selectedSiviso)
	{
		for (OnItemSelectListener onItemClickListener: onItemSelectListeners)
		{
			onItemClickListener.onItemSelect(selectedSiviso);
		}
	}
	
	
	public void notifyDeselect()
	{
		for (OnItemSelectListener onItemClickListener : onItemSelectListeners)
		{
			onItemClickListener.onItemDeselect();
		}
	}
	
	public void add(OnItemSelectListener onItemClickListener)
	{
		onItemSelectListeners.add(onItemClickListener);
	}
}
