package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onMapCircleClickListener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.siviso.Siviso;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class SelectSivisoOnMap implements GoogleMap.OnMapClickListener
{
	Button buttonConfirmAdd;
	GoogleMap map;
	Spinner inputSiviso;
	
	Circle selected = null;
	
	public SelectSivisoOnMap(
	GoogleMap map, Button buttonConfirmAdd,
	Spinner inputSiviso)
	{
		this.buttonConfirmAdd = buttonConfirmAdd;
		this.map = map;
		this.inputSiviso = inputSiviso;
	}
	
	@Override
	public void onMapClick(LatLng latLng)
	{
		buttonConfirmAdd.setEnabled(true);
		
		if (selected != null)
		{
			clearSelected();
		}
		
		String sivisoName = inputSiviso.getSelectedItem().toString();
		
		selected = map.addCircle(new CircleOptions().center(latLng)
		                                            .radius(Defaults.SIVISO_RADIUS)
		                                            .fillColor(Siviso.color(sivisoName))
		                                            .strokeColor(Defaults.SIVISO_STROKE_COLOR)
		                                            .strokeWidth(Defaults.SIVISO_STROKE_WIDTH)
		                        )
		;
		
		inputSiviso.setOnItemSelectedListener(
		new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(
			AdapterView<?> parent, View view, int position, long id)
			{
				String sivisoName = parent.getSelectedItem().toString();
				selected.setFillColor(Siviso.color(sivisoName));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			
			}
		});
	}
	
	private void clearSelected()
	{
		selected.remove();
		selected = null;
	}
	
	public LatLng getSelectedLatLng()
	{
		return selected.getCenter();
	}
}
