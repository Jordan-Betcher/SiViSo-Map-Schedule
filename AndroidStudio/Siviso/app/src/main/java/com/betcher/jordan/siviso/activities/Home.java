package com.betcher.jordan.siviso.activities;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.home.SetMapHomePosition;
import com.betcher.jordan.siviso.actions.home.StartActivityAdd;
import com.betcher.jordan.siviso.actions.home.StartActivityEdit;
import com.betcher.jordan.siviso.activities.home.sivisoMapCircles.SivisoMapCircles;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.EnableButton;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.HighlightSelectionInList;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemSelectListener.ZoomToSelect;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onMapCircleClickListener.TriggerSelectItem;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.betcher.jordan.siviso.service.Siviso;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

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
		
		runPermissions();
		
		switchOnOff = findViewById(R.id.switchOnOff);
		buttonDelete = findViewById(R.id.buttonDelete);
		buttonEdit = findViewById(R.id.buttonEdit);
		
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		linearLayoutManager = new LinearLayoutManager(this);
		recyclerViewSiviso.setLayoutManager(linearLayoutManager);
		
		itemAdapter = new ItemAdapter();
		recyclerViewSiviso.setAdapter(itemAdapter);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		sivisoModel.getAllSivisoData().observe(this, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				itemAdapter.setSivisoDatas(sivisoDatas);
			}
		});
		
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
				SetMapHomePosition.run(map);
				
				SivisoMapCircles sivisoMapCircles = new SivisoMapCircles(map);
				sivisoModel.getAllSivisoData().observe(Home.this, sivisoMapCircles);
				selectItem.addOnItemSelectListener(new ZoomToSelect(map));
				map.setOnCircleClickListener(new TriggerSelectItem(selectItem, sivisoMapCircles));
			}
		});
	}
	
	private void runPermissions()
	{
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
		    PackageManager.PERMISSION_GRANTED &&
		    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
		    PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(this, new String[]
					                                  {Manifest.permission.ACCESS_FINE_LOCATION},
			                                  Defaults.REQUEST_LOCATION_PERMISSION
			                                 );
		}
		
		NotificationManager notificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
		    && !notificationManager.isNotificationPolicyAccessGranted()) {
			
			Intent intent = new Intent(
					android.provider.Settings
							.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
			
			startActivity(intent);
		}
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
			//StartSivisoService.run(this);
			Log.d(TAG, "startSivisoActivity: Start");
			Intent startSivisoService = new Intent(this, Siviso.class);
			ContextCompat.startForegroundService(this, startSivisoService);
			
			SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
			prefs.edit().putBoolean("isServiceRunning", true).apply();
		}
		else
		{
			//StopSivisoService.run(this);
			Log.d(TAG, "stopSivisoActivity: Stop");
			stopService(new Intent(this, Siviso.class));
			
			SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
			prefs.edit().putBoolean("isServiceRunning", false).apply();
		}
	}
}
