package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.actions.home.ZoomToSelected;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;
import com.betcher.jordan.siviso.database.SivisoData;

import java.util.ArrayList;

public class SelectItem
		implements OnItemClickListener
{
	private View selectedView = null;
	private int previousViewColor = 0;
	private int highlightColor = Color.LTGRAY;
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
			if(selectedView != null)
			{
				selectedView.setBackgroundColor(previousViewColor);
			}
			
			selectedSiviso = sivisoRecyclerViewItemAdapter.getItem(itemPosition);
			selectedView = view;
			previousViewColor = ((ColorDrawable)view.getBackground()).getColor();
			view.setBackgroundColor(highlightColor);
			
			callAllOnSelectItemListeners(selectedSiviso);
		}
	}
	
	public SivisoData getSelectedSiviso()
	{
		return selectedSiviso;
	}
	
	public boolean getIsSivisoSelected()
	{
		if (selectedSiviso != null && sivisoRecyclerViewItemAdapter.containItem(selectedSiviso))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void unselect()
	{
		if(selectedView != null)
		{
			selectedView.setBackgroundColor(previousViewColor);
		}
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
}
