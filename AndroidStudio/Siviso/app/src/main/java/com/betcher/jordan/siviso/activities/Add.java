package com.betcher.jordan.siviso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.SetMapAddPosition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Add extends AppCompatActivity
{
	GoogleMap map;
	AppCompatActivity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		activity = this;
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.addMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				SetMapAddPosition.run(activity, map);
			}
		});
	}
	
	public void onClickButtonCancel(View view)
	{
	
	}
	
	public void onClickButtonConfirmAdd(View view)
	{
	}
}
