package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.view.View;

import com.betcher.jordan.siviso.database.SivisoData;

public interface OnItemSelectedListener
{
	void onItemSelect(SivisoData selectedSivisoData);
	
	void onItemDeselect();
}
