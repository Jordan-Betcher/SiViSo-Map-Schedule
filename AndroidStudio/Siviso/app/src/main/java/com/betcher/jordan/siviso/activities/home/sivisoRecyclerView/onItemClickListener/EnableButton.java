package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.view.View;
import android.widget.Button;

import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;
import com.betcher.jordan.siviso.database.SivisoData;

public class EnableButton implements OnItemSelectedListener
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
}
