package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.actions.home.ZoomToSelected;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;
import com.betcher.jordan.siviso.database.SivisoData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;

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
	
	ArrayList<OnItemSelectedListener> onItemSelectedListeners = new ArrayList<>();
	
	public void callAllOnSelectItemListeners(SivisoData selectedSiviso)
	{
		for (OnItemSelectedListener onItemClickListener: onItemSelectedListeners)
		{
			onItemClickListener.onItemSelect(selectedSiviso);
		}
	}
	
	public void addOnItemSelectListener(OnItemSelectedListener onItemClickListener)
	{
		onItemSelectedListeners.add(onItemClickListener);
	}
	
	public void deselect()
	{
		if(selectedSiviso != null)
		{
			for (OnItemSelectedListener onItemClickListener: onItemSelectedListeners)
			{
				onItemClickListener.onItemDeselect();
			}
			
			selectedSiviso = null;
		}
	}
}
