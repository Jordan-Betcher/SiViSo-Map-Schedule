package com.betcher.jordan.sivisomapschedule.Locations;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.betcher.jordan.sivisomapschedule.DeleteButtonLocationSelectListener;
import com.betcher.jordan.sivisomapschedule.R;

import java.util.ArrayList;

public class LocationsSelectOnItemClickListener implements AdapterView.OnItemClickListener
{
	View                              viewPrevious;
	int                               colorHighlight;
	Location                          locationSelected;
	LocationsListViewWrapper          locationsListViewWrapper;
	ArrayList<LocationSelectListener> selectListeners = new ArrayList<>();
	Button[]                          buttons;
	
	public LocationsSelectOnItemClickListener(Context context, LocationsListViewWrapper locationsListViewWrapper, Button... buttons)
	{
		
		this.locationsListViewWrapper = locationsListViewWrapper;
		colorHighlight                = context.getResources()
		                                       .getColor(R.color.common_google_signin_btn_text_light_default);
		this.buttons                  = buttons;
		
		disableButtons();
	}
	
	private void disableButtons()
	{
		toggleButtons(false);
	}
	
	private void enableButtons()
	{
		toggleButtons(true);
	}
	
	private void toggleButtons(boolean onOff)
	{
		for (Button button : buttons)
		{
			button.setEnabled(onOff);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		if (viewPrevious != null)
		{
			viewPrevious.setBackgroundColor(Color.TRANSPARENT);
		}
		
		viewPrevious = view;
		view.setBackgroundColor(colorHighlight);
		locationSelected = locationsListViewWrapper.getLocation(position);
		
		for (LocationSelectListener selectListener : selectListeners)
		{
			selectListener.OnLocationSelected(locationSelected);
		}
		
		enableButtons();
	}
	
	public Location getLocationSelected()
	{
		return locationSelected;
	}
	
	public Integer getLocationSelectedId()
	{
		return locationsListViewWrapper.getId(locationSelected);
	}
	
	public void addOnLocationSelectedListener(LocationSelectListener selectListener)
	{
		selectListeners.add(selectListener);
	}
}
