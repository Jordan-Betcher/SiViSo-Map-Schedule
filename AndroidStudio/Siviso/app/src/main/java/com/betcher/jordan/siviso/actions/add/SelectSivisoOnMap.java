package com.betcher.jordan.siviso.actions.add;

import android.widget.Button;

import com.betcher.jordan.siviso.Defaults;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class SelectSivisoOnMap implements GoogleMap.OnMapClickListener
{
	Button buttonConfirmAdd;
	GoogleMap map;
	
	Circle selected = null;
	
	public SelectSivisoOnMap(GoogleMap map, Button buttonConfirmAdd)
	{
		this.buttonConfirmAdd = buttonConfirmAdd;
		this.map = map;
	}
	
	@Override
	public void onMapClick(LatLng latLng)
	{
		buttonConfirmAdd.setEnabled(true);
		
		if (selected != null)
		{
			clearSelected();
		}
		
		selected = map.addCircle(new CircleOptions().center(latLng)
		                                            .radius(Defaults.ADD_SIVISO_RADIUS)
		                                            .fillColor(Defaults.ADD_SIVISO_FILL_COLOR)
		                                            .strokeColor(Defaults.ADD_SIVISO_STROKE_COLOR)
		                                            .strokeWidth(Defaults.ADD_SIVISO_STROKE_WIDTH)
		                        )
		;
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
