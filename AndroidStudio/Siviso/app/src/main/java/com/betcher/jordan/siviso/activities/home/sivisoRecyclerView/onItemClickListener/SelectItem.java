package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemSelectListener;
import com.betcher.jordan.siviso.database.SivisoData;

public class SelectItem
		implements OnItemClickListener
{
	private SivisoData selectedSiviso = null;
	private ItemAdapter sivisoRecyclerViewItemAdapter;
	
	private SelectItemListener selectListenersAll = new SelectItemListener();
	private SelectItemListener selectListenersDefault = new SelectItemListener();
	private SelectItemListener selectListenersItems = new SelectItemListener();
	
	public SelectItem(ItemAdapter sivisoRecyclerViewItemAdapter)
	{
		this.sivisoRecyclerViewItemAdapter = sivisoRecyclerViewItemAdapter;
	}
	
	@Override
	public void onItemClick(View view, int itemPosition)
	{
		if (itemPosition != RecyclerView.NO_POSITION)
		{
			selectedSiviso = sivisoRecyclerViewItemAdapter.getItem(itemPosition);
			notifySelect(selectedSiviso);
		}
	}
	
	private boolean isDefault(SivisoData selectedSiviso)
	{
		if(selectedSiviso.getName().equals(Defaults.DEFAULT_SIVISO_NAME))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public SivisoData getSelectedSiviso()
	{
		return selectedSiviso;
	}
	
	public void addSelectListenerAll(OnItemSelectListener onItemClickListener)
	{
		selectListenersAll.add(onItemClickListener);
	}
	
	public void addSelectListenerDefault(OnItemSelectListener onItemClickListener)
	{
		selectListenersDefault.add(onItemClickListener);
	}
	
	public void addSelectListenerItem(OnItemSelectListener onItemClickListener)
	{
		selectListenersItems.add(onItemClickListener);
	}
	
	public void notifySelect(SivisoData selectedSiviso)
	{
		selectListenersAll.notifySelect(selectedSiviso);
		
		if(isDefault(selectedSiviso))
		{
			selectListenersDefault.notifySelect(selectedSiviso);
		}
		else
		{
			selectListenersItems.notifySelect(selectedSiviso);
		}
	}
	
	public void notifyDeselect()
	{
		if(selectedSiviso != null)
		{
			selectListenersAll.notifyDeselect();
			
			if(isDefault(selectedSiviso))
			{
				selectListenersDefault.notifyDeselect();
			}
			else
			{
				selectListenersItems.notifyDeselect();
			}
			
			selectedSiviso = null;
		}
	}
}
