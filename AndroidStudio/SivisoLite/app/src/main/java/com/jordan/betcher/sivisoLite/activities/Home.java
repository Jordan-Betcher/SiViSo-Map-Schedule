package com.jordan.betcher.sivisoLite.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.home.action.StartSivisoService;
import com.jordan.betcher.sivisoLite.activities.home.action.StopSivisoService;
import com.jordan.betcher.sivisoLite.activities.home.setup.MapWrapper;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupOnOffSwitch;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupPermissions;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupSivisoSpinnerDefault;
import com.jordan.betcher.sivisoLite.activities.home.setup.SetupSivisoSpinnerHome;

public class Home extends AppCompatActivity
{
	Switch switchOnOff;
	Spinner spinnerDefault;
	Spinner spinnerHome;
	
	CardView cardViewDefault;
	CardView cardViewHome;
	
	MapWrapper mapWrapper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		SetupPermissions.run(this);
		
		switchOnOff = findViewById(R.id.switchOnOff);
		spinnerDefault = findViewById(R.id.spinnerDefault);
		spinnerHome = findViewById(R.id.spinnerHome);
		cardViewDefault = findViewById(R.id.cardViewDefault);
		cardViewHome = findViewById(R.id.cardViewHome);
		
		mapWrapper = new MapWrapper(this);
		
		SetupOnOffSwitch.run(this, switchOnOff);
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
		PreferencesForSivisoLite.setDefaultSiviso(this, spinnerDefault.getSelectedItemPosition());
	}
	
	public void onSpinnerHomeClicked(View view)
	{
		PreferencesForSivisoLite.setHomeSiviso(this, spinnerHome.getSelectedItemPosition());
	}
	
	public void onCardDefaultClicked(View view)
	{
		cardViewDefault.setBackgroundColor(Defaults.DEFAULT_HOME_HIGHLIGHT_COLOR);
		cardViewHome.setBackgroundColor(Defaults.DEFAULT_HOME_NORMAL_COLOR);
		
		mapWrapper.goToCurrentLocation();
	}
	
	public void onCardHomeClicked(View view)
	{
		cardViewHome.setBackgroundColor(Defaults.DEFAULT_HOME_HIGHLIGHT_COLOR);
		cardViewDefault.setBackgroundColor(Defaults.DEFAULT_HOME_NORMAL_COLOR);
		
		mapWrapper.goToHomeLocation();
	}
}
