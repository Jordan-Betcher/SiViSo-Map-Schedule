package com.betcher.jordan.siviso.actions.home;

import android.graphics.Color;
import android.media.audiofx.DynamicsProcessing;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.activities.home.sivisoMapCircles.SivisoMapCircles;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.database.SivisoData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;

public class SelectSivisoFromMap implements GoogleMap.OnCircleClickListener
{
	private static final String TAG = "SelectSivisoFromMap";
	SelectItem selectItem;
	SivisoMapCircles sivisoMapCircles;
	RecyclerView recyclerView;
	
	private int highlightColor = Color.LTGRAY;
	
	public SelectSivisoFromMap(SelectItem selectItem,
	                           SivisoMapCircles sivisoMapCircles,
	                           RecyclerView recyclerView)
	{
		this.selectItem = selectItem;
		this.sivisoMapCircles = sivisoMapCircles;
		this.recyclerView = recyclerView;
	}
	
	@Override
	public void onCircleClick(Circle circle)
	{
		Log.d(TAG, "onCircleClick: ");
		SivisoData selectedSiviso = sivisoMapCircles.getSiviso(circle);
		selectItem.callAllOnSelectItemListeners(selectedSiviso);
		
		ItemAdapter itemAdapter = (ItemAdapter)recyclerView.getAdapter();
		LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
		
		int selectedPosition = itemAdapter.getPosition(selectedSiviso);
		linearLayoutManager.scrollToPosition(selectedPosition);
		View selectedView = linearLayoutManager.getChildAt(selectedPosition);
		selectedView.setBackgroundColor(highlightColor);
	}
}
