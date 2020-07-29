package com.betcher.jordan.siviso.activities.activity_siviso;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.activities.activity_modifySiviso.Activity_Add;
import com.betcher.jordan.siviso.activities.activity_modifySiviso.Activity_Edit;
import com.betcher.jordan.siviso.activities.activity_permission.Activity_Permissions;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener.EnableButton;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener.HighlightSelectionInList;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener.ZoomToCurrentLocation;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemSelectListener.ZoomToSelect;
import com.betcher.jordan.siviso.activities.activity_siviso.sivisoRecyclerView.RecyclerViewAdapter_Siviso;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.betcher.jordan.siviso.service.Service_ManageRingMode;
import com.betcher.jordan.siviso.siviso.Siviso;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Activity_Siviso extends AppCompatActivity
{
	private static final String TAG = "Activity_Siviso";
	
	GoogleMap map;
	RecyclerView recyclerViewSiviso;
	RecyclerViewAdapter_Siviso itemAdapter;
	SivisoModel sivisoModel;
	
	Switch sivisoServiceSwitch;
	Button buttonDelete;
	Button buttonEdit;
	
	SelectItem selectItem;
	
	LinearLayoutManager linearLayoutManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		boolean granted_fineLocation = ActivityCompat
		                               .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
		                               PackageManager.PERMISSION_GRANTED;
		
		NotificationManager notificationManager =
		(NotificationManager) this.getSystemService(
		Context.NOTIFICATION_SERVICE);
		
		boolean granted_notificationPolicy =
		Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
		&& notificationManager.isNotificationPolicyAccessGranted();
		
		if(false == granted_fineLocation || false == granted_notificationPolicy) //Activity_Permissions.allPermissionsGranted(this)
		{
			//startActivityPermissions() //Activity_Permissions activity should be started first and then go to home
			Intent intent_permissions = new Intent(this, Activity_Permissions.class);
			this.startActivityForResult(intent_permissions, 1);
		}
		else
		{
			init();
		}
	}
	
	private Switch setupSivisoServiceSwitch()
	{
		Switch switchOnOff = findViewById(R.id.switchOnOff);
		boolean isServiceRunning = Preferences_Siviso.isServiceRunning(this);
		
		if(isServiceRunning)
		{
			startSivisoService();
			switchOnOff.setChecked(true);
		}
		
		return switchOnOff;
	}
	
	public void onClickButtonAdd(View view) //this should be a class that implements onClickListener and is appended to buttonAdd in constructor
	{
		runActivityAdd();
	}
	
	private void runActivityAdd()
	{
		LatLng mapPosition = map.getCameraPosition().target;
		Activity_Add.run(this, mapPosition);
	}
	
	public void onClickButtonDelete(View view) //Same as onClickButtonAdd
	{
		deleteSelectedItemAndDeselectIt();
	}
	
	private void deleteSelectedItemAndDeselectIt()
	{
		sivisoModel.delete(selectItem.getSelectedSiviso());
		selectItem.notifyDeselect();
	}
	
	public void onClickButtonEdit(View view)
	{
		runActivityEdit();
	}
	
	private void runActivityEdit()
	{
		SivisoData selectedSivisoData = selectItem.getSelectedSiviso();
		Activity_Edit.run(this, selectedSivisoData);
	}
	
	public void onOnOffSwitchClicked(View view) // should be put into the onOffSwitch class
	{
		if (isSivisoServiceSwitchOn())
		{
			startSivisoService();
		}
		else
		{
			stopSivisoService();
		}
	}
	
	private boolean isSivisoServiceSwitchOn()
	{
		return sivisoServiceSwitch.isChecked();
	}
	
	private void stopSivisoService()
	{
		this.stopService(new Intent(this, Service_ManageRingMode.class));
		Preferences_Siviso.saveIsServiceRunning(this, false);
	}
	
	private void startSivisoService()
	{
		Intent startSivisoService = new Intent(this,
		                                       Service_ManageRingMode.class);
		ContextCompat
		.startForegroundService(this, startSivisoService);
		
		Preferences_Siviso.saveIsServiceRunning(this, true);
	}
	
	//get rid of this and have permissions activity be called first
	//this class is called after permissions activity is done
	@Override
	protected void onActivityResult (int requestCode,
	                                 int resultCode,
	                                 Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		init();
	}
	
	private void init()
	{
		setContentView(R.layout.activity_siviso);
		sivisoServiceSwitch = setupSivisoServiceSwitch();
		buttonDelete = findViewById(R.id.buttonDelete);
		buttonEdit = findViewById(R.id.buttonEdit);
		
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		linearLayoutManager = new LinearLayoutManager(this);
		recyclerViewSiviso.setLayoutManager(linearLayoutManager);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		
		//setupItemAdapter
		itemAdapter = new RecyclerViewAdapter_Siviso(this, sivisoModel);
		recyclerViewSiviso.setAdapter(itemAdapter);
		
		sivisoModel.getAllSivisoData().observe(this, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				ArrayList<SivisoData> shownList_siviso = new ArrayList<>();
				
				Siviso defaultSiviso = Preferences_Siviso.defaultSiviso(
				Activity_Siviso.this);
				SivisoData defaultSivisoData = new SivisoData(Defaults.DEFAULT_SIVISO_NAME, defaultSiviso.name(), 0, 0);
				
				shownList_siviso.add(defaultSivisoData);
				shownList_siviso.addAll(sivisoDatas);
				
				itemAdapter.setSivisoDatas(shownList_siviso);
			}
		});
		
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
				
				LocationManager locationManager = (LocationManager) Activity_Siviso
				.this
				.getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);
				
				LocationListenerMapGoToCurrentLocation listener = new LocationListenerMapGoToCurrentLocation(locationManager, map);
				listener.goToCurrentLocation();
				
				selectItem.addSelectListenerDefault(new ZoomToCurrentLocation(listener));
				
				SivisoMapCircles sivisoMapCircles = new SivisoMapCircles(map);
				sivisoModel.getAllSivisoData().observe(
				Activity_Siviso.this, sivisoMapCircles);
				selectItem.addSelectListenerItem(new ZoomToSelect(map));
				map.setOnCircleClickListener(new TriggerSelectItem(selectItem, sivisoMapCircles));
			}
		});
	}
}
