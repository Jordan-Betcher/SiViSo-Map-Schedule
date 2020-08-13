package com.jordan.betcher.siviso.livedatafrompreferences;

import android.view.View;
import android.widget.AdapterView;

public class UpdateHomeRingmode
implements AdapterView.OnItemSelectedListener
{
	Model model;
	
	public UpdateHomeRingmode(Model model)
	{
		this.model = model;
	}
	
	@Override
	public void onItemSelected(
	AdapterView<?> parent, View view, int position, long id)
	{
		model.updateHomeRingmode(Ringmode.values()[position]);
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
	
	}
	
}
