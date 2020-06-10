package com.jordan.betcher.sivisoLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.home.action.StartSivisoService;
import com.jordan.betcher.sivisoLite.activities.home.action.StopSivisoService;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupHomeMap;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupOnOffSwitch;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupSivisoSpinners;

public class Home extends AppCompatActivity
{
	Switch switchOnOff;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		switchOnOff = findViewById(R.id.switchOnOff);
		
		SetupOnOffSwitch.run(this, switchOnOff);
		SetupHomeMap.run(this);
		SetupSivisoSpinners.run(this);
		
	}
	
	public void onOnOffSwitchClicked(View view)
	{
		if(switchOnOff.isChecked())
		{
			StartSivisoService.run(this);
		}
		else
		{
			StopSivisoService.run(this);
		}
	}
}
