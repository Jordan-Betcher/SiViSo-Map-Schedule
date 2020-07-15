package com.betcher.jordan.siviso.siviso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SpinnerAdapter_Siviso extends BaseAdapter
{
	Context context;
	
	int spinnerLayoutItem = android.R.layout.simple_spinner_dropdown_item;
	
	public SpinnerAdapter_Siviso(Context context)
	{
		this.context = context;
	}
	
	@Override
	public int getCount()
	{
		return Siviso.Count();
	}
	
	@Override
	public Object getItem(int index)
	{
		return Siviso.siviso(index);
	}
	
	@Override
	public long getItemId(int index)
	{
		return index;
	}
	
	
	@Override
	public View getView(
	int index, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		
		if(convertView == null)
		{
			convertView = inflater
			.inflate(spinnerLayoutItem, null);
		}
		
		TextView row = (TextView) convertView.findViewById(android.R.id.text1);
		
		ConstraintLayout.LayoutParams layoutParams_fillSpaceProvided
		= new ConstraintLayout.LayoutParams(
			ConstraintLayout.LayoutParams.MATCH_PARENT
			, ConstraintLayout.LayoutParams.MATCH_PARENT);
		
		row.setLayoutParams(layoutParams_fillSpaceProvided);
		row.setBackgroundColor(Siviso.color(index));
		row.setText(Siviso.name(index));
		
		return convertView;
	}
}
