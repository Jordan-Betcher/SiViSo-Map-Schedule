package com.betcher.jordan.siviso.activities.activity_siviso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.betcher.jordan.siviso.database.AndroidViewModel_Siviso;
import com.betcher.jordan.siviso.database.DatabaseFormatted_Siviso;
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
	AndroidViewModel_Siviso sivisoModel;
	
	SelectItem selectItem;
	
	LinearLayoutManager linearLayoutManager;
	
	public static void run(Context context)
	{
		Intent sivisoActivityIntent = new Intent(context, Activity_Siviso.class);
		context.startActivity(sivisoActivityIntent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		boolean allPermissionsGranted = Activity_Permissions.allPermissionsGranted(this);
		if(allPermissionsGranted)
		{
			init();
		}
		else
		{
			//startActivityPermissions() //Activity_Permissions activity should be started first and then go to home
			Intent intent_permissions = new Intent(this, Activity_Permissions.class);
			this.startActivityForResult(intent_permissions, 1);
		}
	}
	
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
		
		setupOnOffSwitch();
		
		Button buttonDelete = setupDeleteButton();
		
		Button buttonEdit = setupEditButton();
		
		setupAddButton();
		
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		linearLayoutManager = new LinearLayoutManager(this);
		recyclerViewSiviso.setLayoutManager(linearLayoutManager);
		
		sivisoModel = ViewModelProviders.of(this).get(
		AndroidViewModel_Siviso.class);
		
		//setupItemAdapter
		itemAdapter = new RecyclerViewAdapter_Siviso(this, sivisoModel);
		recyclerViewSiviso.setAdapter(itemAdapter);
		
		sivisoModel.getAllSivisoData().observe(this, new Observer<List<DatabaseFormatted_Siviso>>()
		{
			@Override
			public void onChanged(@Nullable List<DatabaseFormatted_Siviso> sivisoDatas)
			{
				ArrayList<DatabaseFormatted_Siviso> shownList_siviso = new ArrayList<>();
				
				Siviso defaultSiviso = Preferences_Siviso.defaultSiviso(
				Activity_Siviso.this);
				DatabaseFormatted_Siviso defaultSivisoData = new DatabaseFormatted_Siviso(Defaults.DEFAULT_SIVISO_NAME, defaultSiviso.name(), 0, 0);
				
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
		
		createMap();
	}
	
	private void setupAddButton()
	{
		Button buttonAdd = findViewById(R.id.buttonAdd);
		buttonAdd.setOnClickListener(new RunActivityAdd(this));
	}
	
	private Button setupEditButton()
	{
		Button buttonEdit = findViewById(R.id.buttonEdit);
		buttonEdit.setOnClickListener(new RunActivityEdit(this));
		return buttonEdit;
	}
	
	private Button setupDeleteButton()
	{
		Button buttonDelete = findViewById(R.id.buttonDelete);
		buttonDelete.setOnClickListener(new DeleteSelectedItem());
		return buttonDelete;
	}
	
	private void setupOnOffSwitch()
	{
		Switch onOffSwitch = findViewById(R.id.switchOnOff);
		SivisoServiceSwitch sivisoServiceSwitch = new SivisoServiceSwitch(this, onOffSwitch);
		onOffSwitch.setOnClickListener(new RefreshSivisoServiceSwitch(sivisoServiceSwitch));
	}
	
	private void createMap()
	{
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
	
	private class RefreshSivisoServiceSwitch
	implements View.OnClickListener
	{
		SivisoServiceSwitch sivisoServiceSwitch;
		
		public RefreshSivisoServiceSwitch(SivisoServiceSwitch sivisoServiceSwitch)
		{
			this.sivisoServiceSwitch = sivisoServiceSwitch;
		}
		
		@Override
		public void onClick(View v)
		{
			sivisoServiceSwitch.refresh();
		}
	}
	
	private class DeleteSelectedItem implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			sivisoModel.delete(selectItem.selectedSivisoData());
			selectItem.notifyDeselect();
		}
	}
	
	private class RunActivityEdit implements View.OnClickListener
	{
		Context context;
		
		public RunActivityEdit(
		Context context)
		{
			this.context = context;
		}
		
		@Override
		public void onClick(View v)
		{
			DatabaseFormatted_Siviso selectedSivisoData = selectItem.selectedSivisoData();
			Activity_Edit.run(context, selectedSivisoData);
		}
	}
	
	private class RunActivityAdd implements View.OnClickListener
	{
		Context context;
		
		public RunActivityAdd(
		Context context)
		{
			this.context = context;
		}
		
		@Override
		public void onClick(View v)
		{
			LatLng currentLatLng = map.getCameraPosition().target;
			Activity_Add.run(context, currentLatLng);
		}
	}
}
