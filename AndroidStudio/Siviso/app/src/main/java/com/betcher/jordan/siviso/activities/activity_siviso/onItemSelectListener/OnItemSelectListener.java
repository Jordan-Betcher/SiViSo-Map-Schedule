package com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener;

import com.betcher.jordan.siviso.database.TableRow_Siviso;

public interface OnItemSelectListener
{
	void onItemSelect(TableRow_Siviso selectedSivisoData);
	
	void onItemDeselect();
}
