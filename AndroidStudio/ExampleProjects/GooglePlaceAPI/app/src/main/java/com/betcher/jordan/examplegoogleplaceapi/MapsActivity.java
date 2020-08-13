package com.betcher.jordan.examplegoogleplaceapi;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity //implements OnMapReadyCallback
{
	
	private GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		permissions();
		
		shortcut();
		/*
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		//*/
		
		
	}
	
	//A Method to take you to the activity you are working on
	//Changes based on which activity you are working on
	public void shortcut()
	{
		Intent intent = new Intent(this, ActivitySearch.class);
		startActivity(intent);
	}
	
	public void onClickButtonGoToActivitySearch(View view)
	{
		Intent intent = new Intent(this, ActivitySearch.class);
		startActivity(intent);
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
	/*
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
		
		//mMap.setMyLocationEnabled(true);
	}
	//*/
	
	String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
			Manifest.permission.ACCESS_COARSE_LOCATION};
	
	final int MY_REQUEST_CODE = 1;
	
	public void permissions()
	{
		if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
		                                      Manifest.permission.ACCESS_FINE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(this,
			                                  permissions, MY_REQUEST_CODE
			                                 );
		}
	}
}
