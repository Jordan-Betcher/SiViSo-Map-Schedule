package com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener;

import android.widget.Button;

import com.betcher.jordan.siviso.database.SivisoData;

public class EnableButton implements OnItemSelectListener
{
	Button button;
	
	public EnableButton(Button button)
	{
		this.button = button;
	}
	
	@Override
	public void onItemSelect(SivisoData selectedSivisoData)
	{
		button.setEnabled(true);
	}
	
	@Override
	public void onItemDeselect()
	{
		button.setEnabled(false);
	}
}
