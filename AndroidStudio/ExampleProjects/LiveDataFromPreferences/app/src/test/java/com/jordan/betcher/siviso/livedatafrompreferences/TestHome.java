package com.jordan.betcher.siviso.livedatafrompreferences;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHome
{
	@Test
	public void Ringmode_StoreRingmodeNone_None()
	{
		Home home = new Home(Ringmode.None);
		
		Ringmode ringmode = home.ringmode();
		
		assertEquals(Ringmode.None, ringmode);
	}
	
	@Test
	public void Ringmode_StoreRingmodeSilent_Silent()
	{
		Home home = new Home(Ringmode.Silent);
		
		Ringmode ringmode = home.ringmode();
		
		assertEquals(Ringmode.Silent, ringmode);
	}
}