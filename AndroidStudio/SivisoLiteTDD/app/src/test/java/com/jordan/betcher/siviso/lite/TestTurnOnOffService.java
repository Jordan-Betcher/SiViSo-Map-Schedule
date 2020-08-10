package com.jordan.betcher.siviso.lite;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TestTurnOnOffService
{
	@Test
	public void OnClick_NotRunningService_RunService()
	{
		RunStop fakeService = mock(RunStop.class);
		when(fakeService.running()).thenReturn(false);
		TurnOnOffService turnOnOffService = new TurnOnOffService(fakeService);
		
		turnOnOffService.onClick(null);
		
		verify(fakeService).run();
	}
	
	@Test
	public void OnClick_ServiceRunning_StopService()
	{
		RunStop fakeService = mock(RunStop.class);
		when(fakeService.running()).thenReturn(true);
		TurnOnOffService turnOnOffService = new TurnOnOffService(fakeService);
		
		turnOnOffService.onClick(null);
		
		verify(fakeService).stop();
	}
}