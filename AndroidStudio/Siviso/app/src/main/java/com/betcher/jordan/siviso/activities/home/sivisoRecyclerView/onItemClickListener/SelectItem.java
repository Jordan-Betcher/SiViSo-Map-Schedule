package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemSelectListener;
import com.betcher.jordan.siviso.database.SivisoData;

import java.util.ArrayList;

public class SelectItem
		implements OnItemClickListener
{
	private SivisoData selectedSiviso = null;
	private ItemAdapter sivisoRecyclerViewItemAdapter;
	
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
			callAllOnSelectItemListeners(selectedSiviso);
		}
	}
	
	public SivisoData getSelectedSiviso()
	{
		return selectedSiviso;
	}
	
	ArrayList<OnItemSelectListener> onItemSelectListeners = new ArrayList<>();
	
	public void callAllOnSelectItemListeners(SivisoData selectedSiviso)
	{
		for (OnItemSelectListener onItemClickListener: onItemSelectListeners)
		{
			onItemClickListener.onItemSelect(selectedSiviso);
		}
	}
	
	public void addOnItemSelectListener(OnItemSelectListener onItemClickListener)
	{
		onItemSelectListeners.add(onItemClickListener);
	}
	
	public void deselect()
	{
		if(selectedSiviso != null)
		{
			for (OnItemSelectListener onItemClickListener: onItemSelectListeners)
			{
				onItemClickListener.onItemDeselect();
			}
			
			selectedSiviso = null;
		}
	}
}
