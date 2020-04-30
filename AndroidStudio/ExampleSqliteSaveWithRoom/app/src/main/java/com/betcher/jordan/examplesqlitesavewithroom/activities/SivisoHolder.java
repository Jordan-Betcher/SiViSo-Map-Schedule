package com.betcher.jordan.examplesqlitesavewithroom.activities;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.examplesqlitesavewithroom.R;

class SivisoHolder extends RecyclerView.ViewHolder
{
	private TextView textViewName;
	private Spinner spinnerSiviso;
	
	public SivisoHolder(View itemView) {
		super(itemView);
		textViewName = itemView.findViewById(R.id.textViewHoldName);
		spinnerSiviso = itemView.findViewById(R.id.spinnerHoldSiviso);
	}
	
	public void setName(String name)
	{
		textViewName.setText(name);
	}
	
	public void setSiviso(String siviso)
	{
		ArrayAdapter arrayAdapter = (ArrayAdapter) spinnerSiviso.getAdapter();
		int arrayPositionOfSiviso = arrayAdapter.getPosition(siviso);
		spinnerSiviso.setSelection(arrayPositionOfSiviso);
	}
}
