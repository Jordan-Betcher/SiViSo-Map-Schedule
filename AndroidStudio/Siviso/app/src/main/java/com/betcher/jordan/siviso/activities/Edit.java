package com.betcher.jordan.siviso.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.CancelActivity;
import com.betcher.jordan.siviso.actions.SelectSivisoOnMap;
import com.betcher.jordan.siviso.actions.edit.SetMapEditPosition;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;

public class Edit extends AppCompatActivity
{
	GoogleMap map;
	AppCompatActivity activity;
	Button buttonConfirmEdit;
	SelectSivisoOnMap selectSivisoOnMap;
	TextInputEditText inputName;
	Spinner inputSiviso;
	
	SivisoModel sivisoModel;
	
	int selectedSivisoDataID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		activity = this;
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		buttonConfirmEdit = (Button) this.findViewById(R.id.buttonConfirmEdit);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.editMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				selectSivisoOnMap = new SelectSivisoOnMap(map, buttonConfirmEdit);
				map.setOnMapClickListener(selectSivisoOnMap);
				
				SetMapEditPosition.run(activity, map, selectSivisoOnMap);
			}
		});
		
		inputName = this.findViewById(R.id.editName);
		inputSiviso = this.findViewById(R.id.editSiviso);
		
		
		Intent intent = activity.getIntent();
		selectedSivisoDataID  = intent.getIntExtra(Defaults.EXTRA_NAME_ID, -1);
		inputName.setText(intent.getStringExtra(Defaults.EXTRA_NAME_NAME));
		ArrayAdapter arrayAdapter = (ArrayAdapter) inputSiviso.getAdapter();
		int arrayPositionOfSiviso = arrayAdapter.getPosition(intent.getStringExtra(Defaults.EXTRA_NAME_SIVISO));
		inputSiviso.setSelection(arrayPositionOfSiviso);
	}
	
	public void onClickButtonCancel(View view)
	{
		CancelActivity.run(this);
	}
	
	public void onClickButtonConfirmEdit(View view)
	{
		String name = inputName.getText().toString().trim();
		String siviso = inputSiviso.getSelectedItem().toString();
		LatLng latLng = selectSivisoOnMap.getSelectedLatLng();
		
		SivisoData sivisoData = new SivisoData(name, siviso, latLng.latitude, latLng.longitude);
		sivisoData.setId(selectedSivisoDataID);
		sivisoModel.update(sivisoData);
		this.finish();
	}
}
