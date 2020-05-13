package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView;

import com.betcher.jordan.siviso.database.SivisoData;

public interface OnItemSelectListener
{
	void onItemSelect(SivisoData selectedSivisoData);
	
	void onItemDeselect();
}
