package com.jordan.betcher.siviso.livedatafrompreferences;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDefault
{
	@Test
	public void Ringmode_StoreRingmodeNone_None()
	{
		Default aDefault = new Default(Ringmode.None);
		
		Ringmode ringmode = aDefault.ringmode();
		
		assertEquals(Ringmode.None, ringmode);
	}
	
	@Test
	public void Ringmode_StoreRingmodeSilent_Silent()
	{
		Default aDefault = new Default(Ringmode.Silent);
		
		Ringmode ringmode = aDefault.ringmode();
		
		assertEquals(Ringmode.Silent, ringmode);
	}
}