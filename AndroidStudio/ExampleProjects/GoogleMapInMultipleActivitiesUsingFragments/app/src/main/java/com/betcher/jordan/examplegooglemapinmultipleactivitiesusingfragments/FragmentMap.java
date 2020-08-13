package com.betcher.jordan.examplegooglemapinmultipleactivitiesusingfragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMap extends Fragment
{
	
	//https://stackoverflow.com/questions/22032815/how-to-get-google-maps-object-inside-a-fragment
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.map_fragment, container, false);
		
		SupportMapFragment supportMapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.map);
		/*
		supportMapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				LatLng sydney = new LatLng(-34, 151);
				googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
				googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
			}
		});
		//*/
		
		return view;
	}
	
}
