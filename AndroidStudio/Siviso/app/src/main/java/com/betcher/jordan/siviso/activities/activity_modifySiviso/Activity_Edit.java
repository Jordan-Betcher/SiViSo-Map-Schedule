package com.betcher.jordan.siviso.activities.activity_modifySiviso;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.betcher.jordan.siviso.siviso.Siviso;
import com.betcher.jordan.siviso.siviso.SpinnerAdapter_Siviso;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;

public class Activity_Edit extends AppCompatActivity
{
	GoogleMap map;
	Button buttonConfirmEdit;
	SelectSivisoOnMap selectSivisoOnMap;
	TextInputEditText inputName;
	Spinner inputSiviso;
	
	SivisoModel sivisoModel;
	
	int selectedSivisoDataID;
	
	Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		buttonConfirmEdit = (Button) this.findViewById(R.id.buttonConfirmEdit);
		inputName = this.findViewById(R.id.editName);
		inputSiviso = this.findViewById(R.id.editSiviso);
		
		SpinnerAdapter_Siviso spinnerAdapter_siviso = new SpinnerAdapter_Siviso(this);
		inputSiviso.setAdapter(spinnerAdapter_siviso);
		
		Intent intent = this.getIntent();
		selectedSivisoDataID  = intent.getIntExtra(IntentBuilder.EXTRA_NAME_ID, -1);//Crashes if not found
		String selectedSivisoDataName = intent.getStringExtra(IntentBuilder.EXTRA_NAME_NAME);
		String selectedSivisoDataSiviso = intent.getStringExtra(IntentBuilder.EXTRA_NAME_SIVISO);
		double selectedSivisoDataLatitude = intent.getDoubleExtra(IntentBuilder.EXTRA_NAME_LATITUDE, 0);
		double selectedSivisoDataLongitude = intent.getDoubleExtra(IntentBuilder.EXTRA_NAME_LONGITUDE, 0);
		
		inputName.setText(selectedSivisoDataName);
		
		int index_siviso = Siviso.index(selectedSivisoDataSiviso);
		inputSiviso.setSelection(index_siviso);
		
		activity = this;
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.editMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@SuppressLint("MissingPermission")
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				map.setMyLocationEnabled(true);
				selectSivisoOnMap = new SelectSivisoOnMap(map, buttonConfirmEdit,
				                                          inputSiviso);
				map.setOnMapClickListener(selectSivisoOnMap);
				
				
				//SetMapEditPosition
				Intent intent = activity.getIntent();
				Double latitude  = intent.getDoubleExtra(IntentBuilder.EXTRA_NAME_LATITUDE, 0);
				Double longitude  = intent.getDoubleExtra(IntentBuilder.EXTRA_NAME_LONGITUDE, 0);
				LatLng selectedSivisoDataLatLng = new LatLng(latitude, longitude);
				map.moveCamera(CameraUpdateFactory
				               .newLatLngZoom(selectedSivisoDataLatLng,
				                              Defaults.SIVISO_ZOOM));
				
				selectSivisoOnMap.onMapClick(selectedSivisoDataLatLng);
			}
		});
	}
	
	public void onClickButtonCancel(View view)
	{
		this.finish();
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
	
	public static void run(Context context, SivisoData sivisoData)
	{
		IntentBuilder intentBuilder = new IntentBuilder(context);
		intentBuilder.putExtraSivisoData(sivisoData);
		intentBuilder.runIntent();
	}
	
	private static class IntentBuilder
	{
		final Class<Activity_Edit> INTENT_CLASS = Activity_Edit.class;
		
		public static final String EXTRA_NAME_ID = "id";
		public static final String EXTRA_NAME_NAME = "name";
		public static final String EXTRA_NAME_SIVISO = "siviso";
		public static final String EXTRA_NAME_LATITUDE = "latitude";
		public static final String EXTRA_NAME_LONGITUDE = "longitude";
		
		Context context;
		Intent intent;
		
		public IntentBuilder(Context context)
		{
			this.context = context;
			intent = new Intent(context, INTENT_CLASS);
		}
		
		public void putExtraSivisoData(SivisoData sivisoData)
		{
			intent.putExtra(EXTRA_NAME_ID, sivisoData.id());
			intent.putExtra(EXTRA_NAME_NAME, sivisoData.name());
			intent.putExtra(EXTRA_NAME_SIVISO, sivisoData.siviso().name());
			intent.putExtra(EXTRA_NAME_LATITUDE, sivisoData.latitude());
			intent.putExtra(EXTRA_NAME_LONGITUDE, sivisoData.longitude());
		}
		
		public void runIntent()
		{
			context.startActivity(intent);
		}
	}
}

