package com.betcher.jordan.siviso.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.CancelActivity;
import com.betcher.jordan.siviso.actions.add.SetMapAddPosition;
import com.betcher.jordan.siviso.activities.activity.SpinnerAdapter_Siviso;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onMapCircleClickListener.SelectSivisoOnMap;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;

public class Add extends AppCompatActivity
{
	GoogleMap map;
	AppCompatActivity activity;
	Button buttonConfirmAdd;
	SelectSivisoOnMap selectSivisoOnMap;
	TextInputEditText inputName;
	Spinner inputSiviso;
	
	SivisoModel sivisoModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		activity = this;
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		buttonConfirmAdd = (Button) this.findViewById(R.id.buttonConfirmAdd);
		
		inputName = this.findViewById(R.id.addName);
		inputSiviso = this.findViewById(R.id.addSiviso);
		
		inputSiviso.setAdapter(new SpinnerAdapter_Siviso(this));
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.addMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@SuppressLint("MissingPermission")
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				map.setMyLocationEnabled(true);
				SetMapAddPosition.run(activity, map);
				selectSivisoOnMap = new SelectSivisoOnMap(map, buttonConfirmAdd, inputSiviso);
				map.setOnMapClickListener(selectSivisoOnMap);
			}
		});
	}
	
	public void onClickButtonCancel(View view)
	{
		CancelActivity.run(this);
	}
	
	public void onClickButtonConfirmAdd(View view)
	{
		String name = inputName.getText().toString().trim();
		String siviso = inputSiviso.getSelectedItem().toString();
		LatLng latLng = selectSivisoOnMap.getSelectedLatLng();
		
		SivisoData sivisoData = new SivisoData(name, siviso, latLng.latitude, latLng.longitude);
		sivisoModel.insert(sivisoData);
		this.finish();
	}
}
