package com.betcher.jordan.siviso.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.add.CancelAdd;
import com.betcher.jordan.siviso.actions.add.SelectAddSiviso;
import com.betcher.jordan.siviso.actions.add.SetMapAddPosition;
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
	SelectAddSiviso selectAddSiviso;
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
		
		inputName = this.findViewById(R.id.addName);
		inputSiviso = this.findViewById(R.id.addSiviso);
	}
	
	public void onClickButtonCancel(View view)
	{
		CancelAdd.run(this);
	}
	
	public void onClickButtonConfirmAdd(View view)
	{
		String name = inputName.getText().toString().trim();
		String siviso = inputSiviso.getSelectedItem().toString();
		LatLng latLng = selectAddSiviso.getSelectedLatLng();
		
		SivisoData sivisoData = new SivisoData(name, siviso, latLng.latitude, latLng.longitude);
		sivisoModel.insert(sivisoData);
		this.finish();
	}
}
