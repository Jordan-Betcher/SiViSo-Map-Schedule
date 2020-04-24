package com.betcher.jordan.siviso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.add.CancelAdd;
import com.betcher.jordan.siviso.actions.add.SelectAddSiviso;
import com.betcher.jordan.siviso.actions.add.SetMapAddPosition;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class Add extends AppCompatActivity
{
	GoogleMap map;
	AppCompatActivity activity;
	Button buttonConfirmAdd;
	SelectAddSiviso selectAddSiviso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		activity = this;
		buttonConfirmAdd = (Button) this.findViewById(R.id.buttonConfirmAdd);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.addMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				SetMapAddPosition.run(activity, map);
				selectAddSiviso = new SelectAddSiviso(map, buttonConfirmAdd);
				map.setOnMapClickListener(selectAddSiviso);
			}
		});
	}
	
	public void onClickButtonCancel(View view)
	{
		CancelAdd.run(this);
	}
	
	public void onClickButtonConfirmAdd(View view)
	{
	
	}
}
