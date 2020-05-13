package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.database.SivisoData;

public class HighlightSelectionInList implements OnItemSelectedListener
{
	
	ItemAdapter itemAdapter;
	LinearLayoutManager linearLayoutManager;
	private View highlightedView = null;
	private int previousViewColor = 0;
	
	public HighlightSelectionInList(ItemAdapter itemAdapter, LinearLayoutManager linearLayoutManager)
	{
		this.itemAdapter = itemAdapter;
		this.linearLayoutManager = linearLayoutManager;
	}
	
	@Override
	public void onItemSelect(SivisoData selectedSivisoData)
	{
		int selectedPosition = itemAdapter.getPosition(selectedSivisoData);
		linearLayoutManager.scrollToPosition(selectedPosition);
		View view = linearLayoutManager.getChildAt(selectedPosition);
		
		RevertSelectedViewHighlight();
		Highlight(view);
		
	}
	
	@Override
	public void onItemDeselect()
	{
		RevertSelectedViewHighlight();
	}
	
	private void Highlight(View view)
	{
		highlightedView = view;
		previousViewColor = ((ColorDrawable)view.getBackground()).getColor();
		view.setBackgroundColor(Defaults.ITEM_SELECT_HIGHLIGHT_COLOR);
	}
	
	private void RevertSelectedViewHighlight()
	{
		if(highlightedView != null)
		{
			highlightedView.setBackgroundColor(previousViewColor);
		}
	}
}
