package com.jordan.betcher.sivisoLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.home.action.StartSivisoService;
import com.jordan.betcher.sivisoLite.activities.home.action.StopSivisoService;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupHomeMap;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupOnOffSwitch;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupPermissions;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupSivisoSpinnerDefault;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupSivisoSpinnerHome;

public class Home extends AppCompatActivity
{
	Switch switchOnOff;
	Spinner spinnerDefault;
	Spinner spinnerHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		SetupPermissions.run(this);
		
		switchOnOff = findViewById(R.id.switchOnOff);
		spinnerDefault = findViewById(R.id.spinnerDefault);
		spinnerHome = findViewById(R.id.spinnerHome);
		
		SetupOnOffSwitch.run(this, switchOnOff);
		SetupHomeMap.run(this);
		SetupSivisoSpinnerDefault.run(this, spinnerDefault);
		SetupSivisoSpinnerHome.run(this, spinnerHome);
		
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
	
	public void onSpinnerDefaultClicked(View view)
	{
		SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs
			.edit()
			.putInt(Defaults.PREFERENCE_KEY_SPINNER_DEFAULT, spinnerDefault.getSelectedItemPosition())
			.apply();
	}
	
	public void onSpinnerHomeClicked(View view)
	{
		SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs
			.edit()
			.putInt(Defaults.PREFERENCE_KEY_SPINNER_HOME, spinnerHome.getSelectedItemPosition())
			.apply();
	}
}
