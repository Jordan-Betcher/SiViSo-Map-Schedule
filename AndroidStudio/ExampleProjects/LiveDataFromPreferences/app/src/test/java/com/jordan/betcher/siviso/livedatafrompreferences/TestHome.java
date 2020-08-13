package com.jordan.betcher.siviso.livedatafrompreferences;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import static org.junit.Assert.*;

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
	
	@Test
	public void LatLng_StartNull_null()
	{
		Home home = new Home(Ringmode.None);
		
		LatLng latLng = home.latLng();
		
		assertEquals(null, latLng);
	}
	
	@Test
	public void SetLatLng_StoreLatLng00_LatLng00()
	{
		Home home = new Home(Ringmode.None);
		home.setLatLng(0,0);
		
		LatLng latLng = home.latLng();
		
		assertEquals(new LatLng(0,0), latLng);
	}
}