package com.betcher.jordan.siviso.siviso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerAdapter_Siviso extends ArrayAdapter
{
	Context context;
	
	int spinnerLayoutItem = android.R.layout.simple_spinner_dropdown_item;
	
	public SpinnerAdapter_Siviso(Context context)
	{
		super(context, android.R.layout.simple_spinner_dropdown_item, Siviso.sivisos);
	}
	
	@NonNull
	@Override
	public View getView(
	int position, @Nullable View convertView,
	@NonNull ViewGroup parent)
	{
		View view = super.getView(position, convertView, parent);
		view.setBackgroundColor(Siviso.color(position));
		return view;
	}
	
	@Override
	public View getDropDownView(
	int position, @Nullable View convertView,
	@NonNull ViewGroup parent)
	{
		View view = super.getDropDownView(position, convertView, parent);
		view.setBackgroundColor(Siviso.color(position));
		return view;
	}
}
