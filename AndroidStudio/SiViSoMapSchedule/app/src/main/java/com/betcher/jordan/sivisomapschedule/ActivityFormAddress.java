package com.betcher.jordan.sivisomapschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityFormAddress extends AppCompatActivity implements OnMapReadyCallback
{
	
	public GoogleMap googleMap;
	SupportMapFragment mapFragment;
	
	TextInputEditText textInputName;
	Spinner           spinnerSiViSo;
	
	Button            buttonCancel;
	Button            buttonAdd;
	
	SQLiteLocation databaseLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_form_map);
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.formMap);
		mapFragment.getMapAsync(this);
		/*
		
		textInputName     = (TextInputEditText) this.findViewById(R.id.textViewName);
		spinnerSiViSo     = (Spinner) this.findViewById(R.id.spinnerSiViSo);
		buttonAdd         = (Button) this.findViewById(R.id.buttonAdd);
		buttonCancel      = (Button) this.findViewById(R.id.buttonCancel);
		
		 */
	}
	
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		this.googleMap = googleMap;
		this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		//LatLng currentLocation = new LatLng();
		
		//this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f));
	}
	
	public void onClickButtonCancel(View view)
	{
		goToActivityHome();
	}
	
	public void onClickButtonAdd(View view)
	{
		String name    = textInputName.getText().toString();
		//Get LatLng
		String siviso  = spinnerSiViSo.getSelectedItem().toString();
		
		//databaseLocation.addData(name, address, SiViSo.fromString(siviso));
		
		goToActivityHome();
	}
	
	public void goToActivityHome()
	{
		//Go to home activity
		finish();
	}
}
