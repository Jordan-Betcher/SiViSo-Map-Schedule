package com.betcher.jordan.sivisomapschedule;

import androidx.fragment.app.FragmentActivity;

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

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
	
	private GoogleMap mMap;
	SupportMapFragment mapFragment;
	
	private ListView                          listViewTop;
	private ListView                          listViewBottom;
	private ArrayList<ListItemLocationSiViSo> listTop = new ArrayList<ListItemLocationSiViSo>();
	private ArrayList<ListItemLocationSiViSo> listBottom = new ArrayList<ListItemLocationSiViSo>();
	private LocationSiViSoListAdapter         adapterTop;
	private LocationSiViSoListAdapter         adapterBottom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locations);
		
		
		createTopList();
		createBottomList();
		
		//*
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
		
		//mapFragment.getView().setVisibility(View.GONE);
		
		//*/
		
		
	}
	
	private void createTopList()
	{
		
		listViewTop = (ListView) findViewById(R.id.listTop);
		
		ListItemLocationSiViSo location1 = new ListItemLocationSiViSo("location 1", "silent");
		ListItemLocationSiViSo location2 = new ListItemLocationSiViSo("location 2 test", "vibrate");
		ListItemLocationSiViSo location3 = new ListItemLocationSiViSo("location 3", "sound");
		ListItemLocationSiViSo location4 = new ListItemLocationSiViSo("location 4", "silent");
		
		listTop.add(location1);
		listTop.add(location2);
		listTop.add(location3);
		listTop.add(location4);
		
		adapterTop = new LocationSiViSoListAdapter(this, R.layout.layout_list_item, listTop);
		listViewTop.setAdapter(adapterTop);
		
		
		listViewTop.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> list, View view, int index, long id)
			{
				if (index == listTop.size() - 1)
				{
					if (mapFragment.getView().getVisibility() == View.VISIBLE)
					{
						mapFragment.getView().setVisibility(View.GONE);
					}
					else
					{
						mapFragment.getView().setVisibility(View.VISIBLE);
					}
				}
				else
				{
					for (int topIndex = listTop.size() - 1; topIndex > index; topIndex--)
					{
						ListItemLocationSiViSo item = listTop.get(topIndex);
						listTop.remove(topIndex);
						listBottom.add(0, item);
						
					}
					
					adapterTop.notifyDataSetChanged();
					adapterBottom.notifyDataSetChanged();
					mapFragment.getView().setVisibility(View.VISIBLE);
				}
			}
		});
		
		
	}
	
	
	private void createBottomList()
	{
		
		listViewBottom = (ListView) findViewById(R.id.listBottom);
		
		ListItemLocationSiViSo location5 = new ListItemLocationSiViSo("location 5 das", "silent");
		listBottom.add(location5);
		
		adapterBottom = new LocationSiViSoListAdapter(this, R.layout.layout_list_item, listBottom);
		listViewBottom.setAdapter(adapterBottom);
		
		listViewBottom.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> list, View view, int index, long id)
			{
				for (int numberOfSwaps = 0; numberOfSwaps <= index; numberOfSwaps++)
				{
					ListItemLocationSiViSo item = listBottom.get(0);
					listBottom.remove(0);
					listTop.add(item);
				}
				
				adapterTop.notifyDataSetChanged();
				adapterBottom.notifyDataSetChanged();
				mapFragment.getView().setVisibility(View.VISIBLE);
			}
		});
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
	
	public void onClick(View view)
	{
		mapFragment.getView().setVisibility(View.VISIBLE);
	}
	
	public void onClickTopList(View view)
	{
	
	}
	
	public void onClickBottomList(View view)
	{
	
	}
}
