package com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener;

import com.betcher.jordan.siviso.database.DatabaseFormatted_Siviso;

public interface OnItemSelectListener
{
	void onItemSelect(DatabaseFormatted_Siviso selectedSivisoData);
	
	void onItemDeselect();
}
