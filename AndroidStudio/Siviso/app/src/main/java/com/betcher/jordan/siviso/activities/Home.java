package com.betcher.jordan.siviso.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.home.CheckAndAskPermissions;
import com.betcher.jordan.siviso.actions.home.LocationListenerMapGoToCurrentLocation;
import com.betcher.jordan.siviso.actions.home.SetUpItemAdapater;
import com.betcher.jordan.siviso.actions.home.StartActivityAdd;
import com.betcher.jordan.siviso.actions.home.StartActivityEdit;
import com.betcher.jordan.siviso.actions.home.StartSivisoService;
import com.betcher.jordan.siviso.actions.home.StopSivisoService;
import com.betcher.jordan.siviso.activities.home.sivisoMapCircles.SivisoMapCircles;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.EnableButton;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.HighlightSelectionInList;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.ZoomToCurrentLocation;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.ZoomToSelect;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onMapCircleClickListener.TriggerSelectItem;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Home extends AppCompatActivity
{
	private static final String TAG = "Home";
	
	GoogleMap map;
	RecyclerView recyclerViewSiviso;
	ItemAdapter itemAdapter;
	SivisoModel sivisoModel;
	
	Switch switchOnOff;
	Button buttonDelete;
	Button buttonEdit;
	
	SelectItem selectItem;
	
	LinearLayoutManager linearLayoutManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		CheckAndAskPermissions.run(this);
		
		switchOnOff = setupSwitchOnOff();
		buttonDelete = findViewById(R.id.buttonDelete);
		buttonEdit = findViewById(R.id.buttonEdit);
		
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		linearLayoutManager = new LinearLayoutManager(this);
		recyclerViewSiviso.setLayoutManager(linearLayoutManager);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		itemAdapter = SetUpItemAdapater.run(this, sivisoModel, recyclerViewSiviso);
		selectItem = new SelectItem(itemAdapter);
		itemAdapter.addOnItemClickedListener(selectItem);
		selectItem.addSelectListenerItem(new EnableButton(buttonDelete));
		selectItem.addSelectListenerItem(new EnableButton(buttonEdit));
		selectItem.addSelectListenerAll(new HighlightSelectionInList(itemAdapter, linearLayoutManager));
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(
				R.id.homeMap);
		
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@SuppressLint("MissingPermission")
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				map.setMyLocationEnabled(true);
				
				LocationManager locationManager = (LocationManager) Home
						.this
						.getApplicationContext()
						.getSystemService(Context.LOCATION_SERVICE);
				
				LocationListenerMapGoToCurrentLocation listener = new LocationListenerMapGoToCurrentLocation(locationManager, map);
				listener.goToCurrentLocation();
				
				selectItem.addSelectListenerDefault(new ZoomToCurrentLocation(listener));
				
				SivisoMapCircles sivisoMapCircles = new SivisoMapCircles(map);
				sivisoModel.getAllSivisoData().observe(Home.this, sivisoMapCircles);
				selectItem.addSelectListenerItem(new ZoomToSelect(map));
				map.setOnCircleClickListener(new TriggerSelectItem(selectItem, sivisoMapCircles));
			}
		});
	}
	
	private Switch setupSwitchOnOff()
	{
		Switch switchOnOff = findViewById(R.id.switchOnOff);
		SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs.getBoolean(Defaults.PREFERENCE_KEY_IS_SERVICE_RUNNING, false);
		
		if(isServiceRunning)
		{
			StartSivisoService.run(this);
			switchOnOff.setChecked(true);
		}
		
		return switchOnOff;
	}
	
	public void onClickButtonAdd(View view)
	{
		LatLng mapPosition = map.getCameraPosition().target;
		double latitude = mapPosition.latitude;
		double longitude = mapPosition.longitude;
		
		StartActivityAdd.run(this, latitude, longitude);
	}
	
	public void onClickButtonDelete(View view)
	{
		sivisoModel.delete(selectItem.getSelectedSiviso());
		selectItem.notifyDeselect();
	}
	
	public void onClickButtonEdit(View view)
	{
		SivisoData selectedItem = selectItem.getSelectedSiviso();
		
		StartActivityEdit.run(this, selectedItem);
	}
	
	public void onOnOffSwitchClicked(View view)
	{
		if (switchOnOff.isChecked())
		{
			StartSivisoService.run(this);
		}
		else
		{
			StopSivisoService.run(this);
		}
	}
	
	public void onSelect(View view)
	{
		Toast.makeText(this, selectItem.getSelectedSiviso().getName(), Toast.LENGTH_SHORT).show();
	}
}
