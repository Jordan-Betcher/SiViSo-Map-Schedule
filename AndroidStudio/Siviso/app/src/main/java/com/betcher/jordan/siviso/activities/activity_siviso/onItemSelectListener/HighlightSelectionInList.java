package com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.activity_siviso.sivisoRecyclerView.RecyclerViewAdapter_Siviso;
import com.betcher.jordan.siviso.database.TableRow_Siviso;

public class HighlightSelectionInList implements OnItemSelectListener, RecyclerViewAdapter_Siviso.OnBindViewListener
{
	
	RecyclerViewAdapter_Siviso itemAdapter;
	LinearLayoutManager linearLayoutManager;
	private View highlightedView = null;
	private int previousViewColor = 0;
	private TableRow_Siviso currentSelect = null;
	
	public HighlightSelectionInList(
	RecyclerViewAdapter_Siviso itemAdapter, LinearLayoutManager linearLayoutManager)
	{
		this.itemAdapter = itemAdapter;
		this.linearLayoutManager = linearLayoutManager;
		itemAdapter.addOnBindViewListener(this);
	}
	
	@Override
	public void onItemSelect(
	TableRow_Siviso selectedSivisoData)
	{
		currentSelect = selectedSivisoData;
		int selectedPosition = itemAdapter.getPosition(selectedSivisoData);
		View view = linearLayoutManager.findViewByPosition(selectedPosition);
		
		if(view == null)
		{
			createViewAndThenHighlightAsOnBindViewListener(selectedPosition);
		}
		else if(!linearLayoutManager.isViewPartiallyVisible(view, true, true))
		{
			linearLayoutManager.scrollToPosition(selectedPosition);
			RevertSelectedViewHighlight();
			Highlight(view);
		}
		else
		{
			RevertSelectedViewHighlight();
			Highlight(view);
		}
	}
	
	/*
	The linear layout manager will eventually scroll,
	that will create a view that will need to bound in itemAdapter,
	which will call this class as a listener,
	which will check the currentSelect,
	which will then call the highlight methods
	AN: This is so long an annoying
	 */
	private void createViewAndThenHighlightAsOnBindViewListener(int selectedPosition)
	{
		linearLayoutManager.scrollToPosition(selectedPosition);
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
		currentSelect = null;
		if(highlightedView != null)
		{
			highlightedView.setBackgroundColor(previousViewColor);
		}
	}
	
	@Override
	public void OnBindView(TableRow_Siviso currentSivisoData, View itemView)
	{
		if(currentSelect == currentSivisoData)
		{
			RevertSelectedViewHighlight();
			Highlight(itemView);
		}
	}
}
