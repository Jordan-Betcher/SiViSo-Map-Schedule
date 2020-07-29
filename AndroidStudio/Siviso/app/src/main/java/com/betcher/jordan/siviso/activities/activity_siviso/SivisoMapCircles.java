package com.betcher.jordan.siviso.activities.activity_siviso;

import androidx.lifecycle.Observer;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.database.SivisoData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;

public class SivisoMapCircles
		implements Observer<List<SivisoData>>
{
	private static final String TAG = "SivisoMapCircles";
	GoogleMap map;
	HashMap<Circle, SivisoData> circleSivisoData = new HashMap<>();
	
	public SivisoMapCircles(GoogleMap map)
	{
		this.map = map;
	}
	
	@Override
	public void onChanged(List<SivisoData> sivisoDatas)
	{
		removePreviousCircles();
		createSivisoCircles(sivisoDatas);
	}
	
	private void createSivisoCircles(List<SivisoData> sivisoDatas)
	{
		for (SivisoData sivisoData : sivisoDatas)
		{
			double latitude = sivisoData.latitude();
			double longitude = sivisoData.longitude();
			LatLng latLng = new LatLng(latitude, longitude);
			
			Circle sivisoMapCircles  = map.addCircle(new CircleOptions().center(latLng)
			                                                            .radius(Defaults.SIVISO_RADIUS)
			                                                            .fillColor(sivisoData.siviso().color())
			                                                            .strokeColor(Defaults.SIVISO_STROKE_COLOR)
			                                                            .strokeWidth(Defaults.SIVISO_STROKE_WIDTH)
			                                        );
			sivisoMapCircles.setClickable(true);
			circleSivisoData.put(sivisoMapCircles, sivisoData);
		}
	}
	
	private void removePreviousCircles()
	{
		for(Circle previousCircles : circleSivisoData.keySet())
		{
			previousCircles.remove();
		}
		
		circleSivisoData.clear();
	}
	
	public SivisoData getSiviso(Circle circle)
	{
		return circleSivisoData.get(circle);
	}
}
