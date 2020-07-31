package com.betcher.jordan.siviso.activities.activity_siviso.onItemClickListener;

import com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener.OnItemSelectListener;
import com.betcher.jordan.siviso.database.DatabaseFormatted_Siviso;

import java.util.ArrayList;

public class SelectItemListener
{
	
	
	ArrayList<OnItemSelectListener> onItemSelectListeners = new ArrayList<>();
	
	public void notifySelect(DatabaseFormatted_Siviso selectedSiviso)
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
