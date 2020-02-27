package com.betcher.jordan.examplecustomlistviewfromlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class ListAdapterLocations extends ArrayAdapter<Location>
{
	static final int idOfLayout = R.layout.list_item_location;
	static final int idOfTextViewName = R.id.textViewName;
	static final int idOfTextViewLocation = R.id.textViewAddress;
	static final int idOfTextViewSiViSo = R.id.textViewSiViSo;
	
	Context context;
	
	public ListAdapterLocations(@NonNull Context context,
	                            ArrayList<Location> locations)
	{
		super(context, idOfLayout, locations);
		this.context = context;
	}
	
	//https://youtu.be/E6vE8fqQPTE?t=591
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		Location location = getItem(position);
		
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		convertView = layoutInflater.inflate(idOfLayout, parent, false);
		
		TextView textViewName, textViewLocation, textViewSiViSo;
		
		textViewName = convertView.findViewById(idOfTextViewName);
		textViewLocation = convertView.findViewById(idOfTextViewLocation);
		textViewSiViSo = convertView.findViewById(idOfTextViewSiViSo);
		
		textViewName.setText(location.name);
		textViewLocation.setText(location.address);
		textViewSiViSo.setText(location.siviso.name);
		
		//https://stackoverflow.com/questions/46190386/how-do-i-add-spinner-inside-listview-row-in-android
		//https://stackoverflow.com/questions/2390102/how-to-set-selected-item-of-spinner-by-value-not-by-position
		Spinner spinner = convertView.findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, SiViSo.getValuesAsStrings());
		spinner.setAdapter(adapter);
		spinner.setSelection(SiViSo.indexOf(location.siviso.name));
		
		return convertView;
	}
}
