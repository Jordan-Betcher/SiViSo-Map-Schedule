package com.betcher.jordan.examplegooglemaplocationpicker;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityLocationPicker extends FragmentActivity implements OnMapReadyCallback
{
	
	private GoogleMap mMap;
	private TextView textViewOutputLatitudeLongitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_location_picker);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
		textViewOutputLatitudeLongitude = (TextView) findViewById(R.id.textViewOutputLatitudeLongitude);
	}
	
	
	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
		
		// Add a marker in Sydney and move the camera
		LatLng sydney = new LatLng(-34, 151);
		mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
	}
	
	Marker sivisoMarker = null;
	
	public void onClickButtonConfirmLocation(View view)
	{
		CameraPosition camera = mMap.getCameraPosition();
		LatLng cameraPosition = camera.target;
		
		String cameraPositionString = "";
		cameraPositionString += "Latitude: ";
		cameraPositionString += cameraPosition.latitude;
		cameraPositionString += "\n";
		cameraPositionString += "Longitude: ";
		cameraPositionString += cameraPosition.longitude;
		
		textViewOutputLatitudeLongitude.setText(cameraPositionString);
		
		if(sivisoMarker == null)
		{
			sivisoMarker = mMap.addMarker(new MarkerOptions().position(cameraPosition).title("SiViSo"));
			sivisoMarker.showInfoWindow();
		}
		else
		{
			sivisoMarker.remove();
			sivisoMarker = mMap.addMarker(new MarkerOptions().position(cameraPosition).title("SiViSo"));
			sivisoMarker.showInfoWindow();
		}
	}
}
