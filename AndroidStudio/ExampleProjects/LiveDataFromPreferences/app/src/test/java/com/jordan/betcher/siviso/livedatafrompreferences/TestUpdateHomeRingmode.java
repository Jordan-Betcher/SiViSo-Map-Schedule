package com.jordan.betcher.siviso.livedatafrompreferences;

import android.view.View;
import android.widget.AdapterView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestUpdateHomeRingmode
{
	@Test
	public void onItemSelected_position0_Ringmode0()
	{
		Model model = mock(Model.class);
		UpdateHomeRingmode updateHomeRingmode = new UpdateHomeRingmode(model);
		
		AdapterView adapterView = mock(AdapterView.class);
		View view = mock(View.class);
		updateHomeRingmode.onItemSelected(adapterView, view, 0, 0);
		
		verify(model).updateHomeRingmode(Ringmode.values()[0]);
	}
	
	@Test
	public void onItemSelected_position1_Ringmode1()
	{
		Model model = mock(Model.class);
		UpdateHomeRingmode updateHomeRingmode = new UpdateHomeRingmode(model);
		
		AdapterView adapterView = mock(AdapterView.class);
		View view = mock(View.class);
		updateHomeRingmode.onItemSelected(adapterView, view, 1, 0);
		
		verify(model).updateHomeRingmode(Ringmode.values()[1]);
	}
}