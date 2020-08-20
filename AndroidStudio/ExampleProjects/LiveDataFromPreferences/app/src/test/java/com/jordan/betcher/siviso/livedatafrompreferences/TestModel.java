package com.jordan.betcher.siviso.livedatafrompreferences;

import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestModel
{
	@Test
	public void updateHomeRingmode_None_VerifySetValue()
	{
		final MutableLiveData<Home> fakeMutableLiveData = mock(MutableLiveData.class);
		
		Model model = new Model()
		{
			@Override
			protected MutableLiveData<Home> createHomeMutableLiveData()
			{
				return fakeMutableLiveData;
			}
		};
		
		model.updateHomeRingmode(Ringmode.None);
		Home expectedInput = new Home(Ringmode.None);
		verify(fakeMutableLiveData).setValue(expectedInput);
	}
	
	@Test
	public void updateHomeRingmode_Silent_VerifySetValue()
	{
		final MutableLiveData<Home> fakeMutableLiveData = mock(MutableLiveData.class);
		
		Model model = new Model()
		{
			@Override
			protected MutableLiveData<Home> createHomeMutableLiveData()
			{
				return fakeMutableLiveData;
			}
		};
		
		model.updateHomeRingmode(Ringmode.Silent);
		Home expectedInput = new Home(Ringmode.Silent);
		verify(fakeMutableLiveData).setValue(expectedInput);
	}
}