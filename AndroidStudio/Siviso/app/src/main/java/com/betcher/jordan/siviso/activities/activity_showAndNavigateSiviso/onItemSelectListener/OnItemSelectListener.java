package com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemSelectListener;

import com.betcher.jordan.siviso.database.SivisoData;

public interface OnItemSelectListener
{
	void onItemSelect(SivisoData selectedSivisoData);
	
	void onItemDeselect();
}
