package com.jordan.betcher.siviso.livedatafrompreferences;

import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestObserverTextViewRingmode
{
	
	@Test
	public void onChanged_homeRingmodeNone_None()
	{
		TextView textView = mock(TextView.class);
		ObserverTextViewRingmode observerTextViewRingmode = new ObserverTextViewRingmode(textView);
		
		Home home = mock(Home.class);
		when(home.ringmode()).thenReturn(Ringmode.None);
		observerTextViewRingmode.onChanged(home);
		
		verify(textView).setText(Ringmode.None.toString());
	}
	
	@Test
	public void onChanged_homeRingmodeSilent_Silent()
	{
		TextView textView = mock(TextView.class);
		ObserverTextViewRingmode observerTextViewRingmode = new ObserverTextViewRingmode(textView);
		
		Home home = mock(Home.class);
		when(home.ringmode()).thenReturn(Ringmode.Silent);
		observerTextViewRingmode.onChanged(home);
		
		verify(textView).setText(Ringmode.Silent.toString());
	}
	
}