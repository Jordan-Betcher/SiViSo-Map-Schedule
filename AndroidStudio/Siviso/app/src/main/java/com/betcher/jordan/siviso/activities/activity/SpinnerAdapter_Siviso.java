package com.betcher.jordan.siviso.activities.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.betcher.jordan.siviso.Defaults;

import java.util.ArrayList;

public class SpinnerAdapter_Siviso extends BaseAdapter
{
	Context context;
	
	ArrayList<String> items = createItems();
	private ArrayList<String> createItems()
	{
		ArrayList<String> items = new ArrayList<>();
		
		items.add("None");
		items.add("Silent");
		items.add("Vibrate");
		items.add("Sound");
		
		return items;
	}
	
	public SpinnerAdapter_Siviso(Context context)
	{
		this.context = context;
	}
	
	@Override
	public int getCount()
	{
		return items.size();
	}
	
	@Override
	public Object getItem(int index)
	{
		return items.get(index);
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
		String siviso = items.get(index);
		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(android.R.layout.simple_spinner_item, null);
		
		TextView row = (TextView) convertView.findViewById(android.R.id.text1);
		row.setBackgroundColor(Defaults.SIVISO_TO_COLOR.get(siviso));
		row.setText(siviso);
		
		return row;
	}
}
