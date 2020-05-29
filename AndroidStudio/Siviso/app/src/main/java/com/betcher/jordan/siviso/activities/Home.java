package com.betcher.jordan.siviso.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.home.CheckAndAskPermissions;
import com.betcher.jordan.siviso.actions.home.MapGoToCurrentLocation;
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
		selectItem.addOnItemSelectListener(new EnableButton(buttonDelete));
		selectItem.addOnItemSelectListener(new EnableButton(buttonEdit));
		selectItem.addOnItemSelectListener(new HighlightSelectionInList(itemAdapter, linearLayoutManager));
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(
				R.id.homeMap);
		
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				map.setMyLocationEnabled(true);
				MapGoToCurrentLocation.run(Home.this, map);
				
				SivisoMapCircles sivisoMapCircles = new SivisoMapCircles(map);
				sivisoModel.getAllSivisoData().observe(Home.this, sivisoMapCircles);
				selectItem.addOnItemSelectListener(new ZoomToSelect(map));
				map.setOnCircleClickListener(new TriggerSelectItem(selectItem, sivisoMapCircles));
			}
		});
	}
	
	private Switch setupSwitchOnOff()
	{
		Switch switchOnOff = findViewById(R.id.switchOnOff);
		SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs.getBoolean("isServiceRunning", false);
		
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
		selectItem.deselect();
		sivisoModel.delete(selectItem.getSelectedSiviso());
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
}
