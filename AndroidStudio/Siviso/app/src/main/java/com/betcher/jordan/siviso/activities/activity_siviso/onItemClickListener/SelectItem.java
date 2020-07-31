package com.betcher.jordan.siviso.activities.activity_siviso.onItemClickListener;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.activity_siviso.sivisoRecyclerView.RecyclerViewAdapter_Siviso;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener.OnItemSelectListener;
import com.betcher.jordan.siviso.database.TableRow_Siviso;

public class SelectItem
		implements OnItemClickListener
{
	private TableRow_Siviso selectedSiviso = null;
	private RecyclerViewAdapter_Siviso sivisoRecyclerViewItemAdapter;
	
	private SelectItemListener selectListenersAll = new SelectItemListener();
	private SelectItemListener selectListenersDefault = new SelectItemListener();
	private SelectItemListener selectListenersItems = new SelectItemListener();
	
	public SelectItem(
	RecyclerViewAdapter_Siviso sivisoRecyclerViewItemAdapter)
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
	
	private boolean isDefault(TableRow_Siviso selectedSiviso)
	{
		if(selectedSiviso.name().equals(Defaults.DEFAULT_SIVISO_NAME))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public TableRow_Siviso selectedSivisoData()
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
	
	public void notifySelect(TableRow_Siviso selectedSiviso)
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
