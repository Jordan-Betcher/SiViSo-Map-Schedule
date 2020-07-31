package com.betcher.jordan.siviso.activities.activity_modifySiviso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.database.AndroidViewModel_Siviso;
import com.betcher.jordan.siviso.database.DatabaseFormatted_Siviso;
import com.betcher.jordan.siviso.siviso.SpinnerAdapter_Siviso;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;

public class Activity_Add extends AppCompatActivity
{
	
	GoogleMap map;
	AppCompatActivity activity;
	Button buttonConfirmAdd;
	SelectSivisoOnMap selectSivisoOnMap;
	TextInputEditText inputName;
	Spinner inputSiviso;
	
	AndroidViewModel_Siviso sivisoModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		activity = this;
		sivisoModel = ViewModelProviders.of(this).get(
		AndroidViewModel_Siviso.class);
		buttonConfirmAdd = (Button) this.findViewById(R.id.buttonAdd);
		
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
				
				//set map position
				Intent intent = activity.getIntent();
				Double latitude  = intent.getDoubleExtra(
				IntentBuilder.EXTRA_NAME_LATITUDE, 0);
				Double longitude  = intent.getDoubleExtra(
				IntentBuilder.EXTRA_NAME_LONGITUDE, 0);
				LatLng previousActivityLatLng = new LatLng(latitude, longitude);
				map.moveCamera(CameraUpdateFactory
				               .newLatLngZoom(previousActivityLatLng,
				                              Defaults.SIVISO_ZOOM));
				selectSivisoOnMap = new SelectSivisoOnMap(map, buttonConfirmAdd, inputSiviso);
				map.setOnMapClickListener(selectSivisoOnMap);
			}
		});
	}
	
	public void onClickButtonCancel(View view)
	{
		this.finish();
	}
	
	public void onClickButtonConfirmAdd(View view)
	{
		String name = inputName.getText().toString().trim();
		String siviso = inputSiviso.getSelectedItem().toString();
		LatLng latLng = selectSivisoOnMap.getSelectedLatLng();
		
		DatabaseFormatted_Siviso sivisoData = new DatabaseFormatted_Siviso(name, siviso, latLng.latitude, latLng.longitude);
		sivisoModel.insert(sivisoData);
		this.finish();
	}
	
	
	public static void run(Context context, LatLng mapPosition)
	{
		IntentBuilder intentBuilder = new IntentBuilder(context);
		intentBuilder.putExtraLatLng(mapPosition);
		intentBuilder.runIntent();
	}
	
	private static class IntentBuilder
	{
		final Class<Activity_Add> INTENT_CLASS = Activity_Add.class;
		
		static final String EXTRA_NAME_LATITUDE = "latitude";
		static final String EXTRA_NAME_LONGITUDE = "longitude";
		
		Context context;
		Intent intent;
		
		public IntentBuilder(Context context)
		{
			this.context = context;
			intent = new Intent(context, INTENT_CLASS);
		}
		
		public void putExtraLatLng(LatLng latLng)
		{
			intent.putExtra(EXTRA_NAME_LATITUDE, latLng.latitude);
			intent.putExtra(EXTRA_NAME_LONGITUDE, latLng.longitude);
		}
		
		public void runIntent()
		{
			context.startActivity(intent);
		}
	}
}
