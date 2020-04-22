package com.betcher.jordan.sivisomapschedule.SivisoLocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betcher.jordan.sivisomapschedule.R;
import com.betcher.jordan.sivisomapschedule.Siviso;

import java.util.ArrayList;

class SivisoLocationsListAdapter extends ArrayAdapter<SivisoLocation>
{
	static final int idOfLayout = R.layout.list_item_location;
	static final int idOfTextViewName = R.id.textViewName;
	
	Context context;
	
	public SivisoLocationsListAdapter(@NonNull Context context,
	                                  ArrayList<SivisoLocation> sivisoLocations)
	{
		super(context, idOfLayout, sivisoLocations);
		this.context = context;
	}
	
	//https://youtu.be/E6vE8fqQPTE?t=591
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		SivisoLocation sivisoLocation = getItem(position);
		
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		convertView = layoutInflater.inflate(idOfLayout, parent, false);
		
		TextView textViewName;
		
		textViewName = convertView.findViewById(idOfTextViewName);
		
		textViewName.setText(sivisoLocation.getName());
		textViewName.setFocusable(false);
		
		//https://stackoverflow.com/questions/46190386/how-do-i-add-spinner-inside-listview-row-in-android
		//https://stackoverflow.com/questions/2390102/how-to-set-selected-item-of-spinner-by-value-not-by-position
		Spinner spinner = convertView.findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Siviso
				.getValuesAsStrings());
		spinner.setAdapter(adapter);
		spinner.setSelection(Siviso.indexOf(sivisoLocation.getSiviso().name));
		spinner.setFocusable(false);
		
		return convertView;
	}
}
