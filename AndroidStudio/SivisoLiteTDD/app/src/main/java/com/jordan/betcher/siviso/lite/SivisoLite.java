package com.jordan.betcher.siviso.lite;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SivisoLite extends FragmentActivity
{
	private ServiceSiviso service = new ServiceSiviso();
	//private DatabaseSiviso database = new DatabaseSiviso();
	//private SelectedSiviso selected = new SelectedSiviso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_siviso_lite);
		
		setupInitialState();
		setupOnClickResponses();
	}
	
	private void setupInitialState()
	{
		OnOffSwitch_SetToMatch_ServiceRunningState();
		
		
		/*
		setupOnOffButton();     service start / stop
		setupMap();             database show, location show
		setupCardViewDefault(); location select current location
		setupCardViewHome();    location select home
		setupSpinnerDefault();  database set default ringmode
		setupSpinnerHome();     database set home ringmode
		//*/
	}
	
	private void OnOffSwitch_SetToMatch_ServiceRunningState()
	{
		Switch onOffSwitch = OnOffSwitch();
		boolean isServiceRunning = service.running();
		onOffSwitch.setChecked(isServiceRunning);
	}
	
	private Switch OnOffSwitch()
	{
		return (Switch) findViewById(R.id.switchOnOff);
	}
	
	private void setupOnClickResponses()
	{
		OnOffSwitch_SetOnClick_TurnOnOffService();
	}
	
	private void OnOffSwitch_SetOnClick_TurnOnOffService()
	{
		Switch onOffSwitch = OnOffSwitch();
		onOffSwitch.setOnClickListener(new TurnOnOffService(service));
	}
}