package com.jordan.betcher.siviso.lite;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestServiceSiviso
{
	@Test
	public void isRunning_JustCreated_False()
	{
		ServiceSiviso service = new ServiceSiviso();
		
		boolean isRunning = service.running();
		
		assertEquals(false, isRunning);
	}
}