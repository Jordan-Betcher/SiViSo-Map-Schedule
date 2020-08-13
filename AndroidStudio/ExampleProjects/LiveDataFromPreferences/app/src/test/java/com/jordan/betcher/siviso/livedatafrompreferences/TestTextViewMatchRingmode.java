package com.jordan.betcher.siviso.livedatafrompreferences;

import android.widget.TextView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestTextViewMatchRingmode
{
	
	@Test
	public void onChanged_homeRingmodeNone_None()
	{
		TextView textView = mock(TextView.class);
		TextViewMatchRingmode textViewMatchRingmode = new TextViewMatchRingmode(textView);
		
		Home home = mock(Home.class);
		when(home.ringmode()).thenReturn(Ringmode.None);
		textViewMatchRingmode.onChanged(home);
		
		verify(textView).setText(Ringmode.None.toString());
	}
	
	@Test
	public void onChanged_homeRingmodeSilent_Silent()
	{
		TextView textView = mock(TextView.class);
		TextViewMatchRingmode textViewMatchRingmode = new TextViewMatchRingmode(textView);
		
		Home home = mock(Home.class);
		when(home.ringmode()).thenReturn(Ringmode.Silent);
		textViewMatchRingmode.onChanged(home);
		
		verify(textView).setText(Ringmode.Silent.toString());
	}
	
}