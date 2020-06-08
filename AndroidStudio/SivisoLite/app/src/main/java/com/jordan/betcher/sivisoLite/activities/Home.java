package com.jordan.betcher.sivisoLite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.home.SetupHomeMap;

public class Home extends AppCompatActivity
{
	GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		SetupHomeMap.run(this);
	}
	
	public void onOnOffSwitchClicked(View view)
	{
	
	}
}
