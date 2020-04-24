package com.betcher.jordan.siviso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.home.SetMapHomePosition;
import com.betcher.jordan.siviso.actions.home.StartActivityAdd;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Home extends AppCompatActivity
{
	GoogleMap map;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.homeMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				SetMapHomePosition.run(map);
			}
		});
	}
	
	public void onClickButtonAdd(View view)
	{
		LatLng mapPosition = map.getCameraPosition().target;
		StartActivityAdd.run(this, mapPosition);
	}
	
	public void onClickButtonDelete(View view)
	{
	
	}
	
	public void onClickButtonEdit(View view)
	{
	
	}
}
