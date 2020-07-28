package com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.betcher.jordan.siviso.activities.activity_permission.Permissions;
import com.betcher.jordan.siviso.activities.activity_modifySiviso.Add;
import com.betcher.jordan.siviso.activities.activity_modifySiviso.Edit;
import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.sivisoRecyclerView.RecyclerViewAdapter_Siviso;
import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemSelectListener.EnableButton;
import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemSelectListener.HighlightSelectionInList;
import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemSelectListener.ZoomToCurrentLocation;
import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.onItemSelectListener.ZoomToSelect;
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

public class Home extends AppCompatActivity
{
	private static final String TAG = "Home";
	
	GoogleMap map;
	RecyclerView recyclerViewSiviso;
	RecyclerViewAdapter_Siviso itemAdapter;
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
		
		boolean granted_fineLocation = ActivityCompat
		                               .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
		                               PackageManager.PERMISSION_GRANTED;
		
		NotificationManager notificationManager =
		(NotificationManager) this.getSystemService(
		Context.NOTIFICATION_SERVICE);
		
		boolean granted_notificationPolicy =
		Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
		&& notificationManager.isNotificationPolicyAccessGranted();
		
		if(false == granted_fineLocation || false == granted_notificationPolicy) //Permissions.allPermissionsGranted(this)
		{
			//startActivityPermissions() //Permissions activity should be started first and then go to home
			Intent intent_permissions = new Intent(this, Permissions.class);
			this.startActivityForResult(intent_permissions, 1);
		}
		else
		{
			init();
		}
	}
	
	private Switch setupSwitchOnOff() //this should be it's own class
	{
		Switch switchOnOff = findViewById(R.id.switchOnOff);
		boolean isServiceRunning = Preferences_Siviso.isServiceRunning(this);
		
		if(isServiceRunning)
		{
			//StartSivisoService.run(this);
			Log.d(TAG, "run: Starting Service_ManageRingMode Service");
			Intent startSivisoService = new Intent(this, Service_ManageRingMode.class);
			ContextCompat
			.startForegroundService(this, startSivisoService);
			
			Preferences_Siviso.saveIsServiceRunning(this, true);
			
			
			switchOnOff.setChecked(true);
		}
		
		return switchOnOff;
	}
	
	public void onClickButtonAdd(View view) //this should be a class that implements onClickListener and is appended to buttonAdd in constructor
	{
		LatLng mapPosition = map.getCameraPosition().target;
		double latitude = mapPosition.latitude;
		double longitude = mapPosition.longitude;
		
		//StartActivityAdd.run(this, latitude, longitude);
		Intent myIntent = new Intent(this, Add.class);
		myIntent.putExtra(Defaults.EXTRA_NAME_LATITUDE, latitude);
		myIntent.putExtra(Defaults.EXTRA_NAME_LONGITUDE, longitude);
		this.startActivity(myIntent);
	}
	
	public void onClickButtonDelete(View view) //Same as onClickButtonAdd
	{
		sivisoModel.delete(selectItem.getSelectedSiviso());
		selectItem.notifyDeselect();
	}
	
	public void onClickButtonEdit(View view) //Same as onClickButtonAdd
	{
		SivisoData selectedSivisoData = selectItem.getSelectedSiviso();
		
		//StartActivityEdit.run(this, selectedItem);
		Intent myIntent = new Intent(this, Edit.class);
		myIntent.putExtra(Defaults.EXTRA_NAME_ID, selectedSivisoData.id());
		myIntent.putExtra(Defaults.EXTRA_NAME_NAME, selectedSivisoData.name());
		myIntent.putExtra(Defaults.EXTRA_NAME_SIVISO, selectedSivisoData.siviso().name());
		myIntent.putExtra(Defaults.EXTRA_NAME_LATITUDE, selectedSivisoData.latitude());
		myIntent.putExtra(Defaults.EXTRA_NAME_LONGITUDE, selectedSivisoData.longitude());
		this.startActivity(myIntent);
	}
	
	public void onOnOffSwitchClicked(View view) // should be put into the onOffSwitch class
	{
		if (switchOnOff.isChecked())
		{
			//StartSivisoService.run(this);
			Intent startSivisoService = new Intent(this, Service_ManageRingMode.class);
			ContextCompat.startForegroundService(this, startSivisoService);
			
			Preferences_Siviso.saveIsServiceRunning(this, true);
		}
		else
		{
			//StopSivisoService.run(this);
			this.stopService(new Intent(this, Service_ManageRingMode.class));
			Preferences_Siviso.saveIsServiceRunning(this, false);
		}
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
		setContentView(R.layout.activity_home);
		switchOnOff = setupSwitchOnOff();
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
				
				Siviso defaultSiviso = Preferences_Siviso.defaultSiviso(Home.this);
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
}
