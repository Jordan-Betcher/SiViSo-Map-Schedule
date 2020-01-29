package com.betcher.jordan.sivisomapschedule;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
	
	private GoogleMap mMap;
	SupportMapFragment mapFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		/*
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		mapFragment.getView().setVisibility(View.GONE);
		//*/
		
	}
	
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
		
		LatLng sydney = new LatLng(-34, 151);
		mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
		Geocoder geocoder = new Geocoder(this);
		LatLng cameraLocation = mMap.getCameraPosition().target;
		try
		{
			List<Address> addresses = geocoder.getFromLocation(cameraLocation.latitude, cameraLocation.longitude, 10);
			
			for(Address address : addresses)
			{
				Log.d("TEST", "Address: " + address);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void onClick(View view)
	{
		mapFragment.getView().setVisibility(View.VISIBLE);
	}
}
