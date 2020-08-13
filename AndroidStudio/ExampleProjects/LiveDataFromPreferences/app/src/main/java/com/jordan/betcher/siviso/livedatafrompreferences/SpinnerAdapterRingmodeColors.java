package com.jordan.betcher.siviso.livedatafrompreferences;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerAdapterRingmodeColors extends ArrayAdapter
{
	public SpinnerAdapterRingmodeColors(Context context)
	{
		super(context, android.R.layout.simple_spinner_dropdown_item, Ringmode.values());
	}
	
	@NonNull
	@Override
	public View getView(
	int position, @Nullable View convertView,
	@NonNull ViewGroup parent)
	{
		View view = super.getView(position, convertView, parent);
		//view.setBackgroundColor(ringmodeColor.get(position).color());
		return view;
	}
	
	@Override
	public View getDropDownView(
	int position, @Nullable View convertView,
	@NonNull ViewGroup parent)
	{
		View view = super.getDropDownView(position, convertView, parent);
		//view.setBackgroundColor(SivisoRingmode.color(position));
		return view;
	}
	
}
