package com.betcher.jordan.sivisomapschedule.SivisoLocation;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.betcher.jordan.sivisomapschedule.R;

import java.util.ArrayList;

public class SivisoLocationsSelectOnItemClickListener implements AdapterView.OnItemClickListener
{
	View                                    viewPrevious;
	int                                     colorHighlight;
	SivisoLocation                          sivisoLocationSelected;
	SivisoLocationsListViewWrapper          sivisoLocationsListViewWrapper;
	ArrayList<SivisoLocationSelectListener> selectListeners = new ArrayList<>();
	Button[]                                buttons;
	
	public SivisoLocationsSelectOnItemClickListener(Context context, SivisoLocationsListViewWrapper sivisoLocationsListViewWrapper, Button... buttons)
	{
		
		this.sivisoLocationsListViewWrapper = sivisoLocationsListViewWrapper;
		colorHighlight                      = context.getResources()
		                                       .getColor(R.color.common_google_signin_btn_text_light_default);
		this.buttons                        = buttons;
		
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
		sivisoLocationSelected = sivisoLocationsListViewWrapper.getLocation(position);
		
		for (SivisoLocationSelectListener selectListener : selectListeners)
		{
			selectListener.OnLocationSelected(sivisoLocationSelected);
		}
		
		enableButtons();
	}
	
	public SivisoLocation getSivisoLocationSelected()
	{
		return sivisoLocationSelected;
	}
	
	public Integer getLocationSelectedId()
	{
		return sivisoLocationsListViewWrapper.getId(sivisoLocationSelected);
	}
	
	public void addOnLocationSelectedListener(SivisoLocationSelectListener selectListener)
	{
		selectListeners.add(selectListener);
	}
}
