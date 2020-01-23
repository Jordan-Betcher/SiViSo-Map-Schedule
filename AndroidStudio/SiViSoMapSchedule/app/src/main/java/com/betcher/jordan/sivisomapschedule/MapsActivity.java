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
		setContentView(R.layout.activity_locations);
		
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		mapFragment.getView().setVisibility(View.GONE);
		
		SwapperItemList swapperItemList = new SwapperItemList(this, R.layout.layout_list_item, R.id.listTop, R.id.listBottom, mapFragment);
		
		ListItemLocationSiViSo location1 = new ListItemLocationSiViSo("location 1", "silent");
		ListItemLocationSiViSo location2 = new ListItemLocationSiViSo("location 2 test", "vibrate");
		ListItemLocationSiViSo location3 = new ListItemLocationSiViSo("location 3", "sound");
		ListItemLocationSiViSo location4 = new ListItemLocationSiViSo("location 4", "silent");
		ListItemLocationSiViSo location5 = new ListItemLocationSiViSo("location 5 das", "silent");
		
		swapperItemList.AddListItem(location1);
		swapperItemList.AddListItem(location2);
		swapperItemList.AddListItem(location3);
		swapperItemList.AddListItem(location4);
		swapperItemList.AddListItem(location5);
		
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
