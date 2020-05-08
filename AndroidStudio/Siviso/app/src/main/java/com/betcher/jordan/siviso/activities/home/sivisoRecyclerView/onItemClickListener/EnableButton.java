package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener;

import android.view.View;
import android.widget.Button;

import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.OnItemClickListener;

public class EnableButton implements OnItemClickListener
{
	Button button;
	
	public EnableButton(Button button)
	{
		this.button = button;
	}
	
	@Override
	public void onItemClick(View view, int itemPosition)
	{
		button.setEnabled(true);
	}
}
