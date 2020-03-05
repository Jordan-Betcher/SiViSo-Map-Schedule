package com.betcher.jordan.sivisomapschedule;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
	private static final int REQUEST_LOCATION_PERMISSION = 1;
	
	public GoogleMap googleMap;
	SupportMapFragment mapFragment;
	
	TextView          title;
	Switch            switchOnOff;
	ConstraintLayout  form;
	TextInputEditText textInputName;
	Spinner           spinnerSiViSo;
	TextInputEditText textInputAddress;
	Button            buttonCancel;
	Button            buttonConfirm;
	Button            buttonAdd;
	Button            buttonDelete;
	Button            buttonEdit;
	ListView          listViewLocations;
	
	SQLiteLocation       databaseLocation;
	ListAdapterLocations listAdapterLocations;
	
	Location locationCurrent;
	
	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
		title             = (TextView) this.findViewById(R.id.textViewTitle);
		switchOnOff       = (Switch) this.findViewById(R.id.switchOnOff);
		form              = (ConstraintLayout) this.findViewById(R.id.form);
		textInputName     = (TextInputEditText) this.findViewById(R.id.textViewName);
		spinnerSiViSo     = (Spinner) this.findViewById(R.id.spinnerSiViSo);
		textInputAddress  = (TextInputEditText) this.findViewById(R.id.textInputAddress);
		listViewLocations = (ListView) this.findViewById(R.id.listViewLocations);
		buttonAdd         = (Button) this.findViewById(R.id.buttonAddLocation);
		buttonCancel      = (Button) this.findViewById(R.id.buttonCancel);
		buttonDelete      = (Button) this.findViewById(R.id.buttonDelete);
		buttonEdit        = (Button) this.findViewById(R.id.buttonEdit);
		buttonConfirm     = (Button) this.findViewById(R.id.buttonConfirm);
		
		databaseLocation = new SQLiteLocation(this);
		//Test
		databaseLocation.addData("test", "test address", SiViSo.SILENT);
		
		makeMapFollowCurrentLocation();
		showListViewLocations();
		
		setStateHome();
	}
	
	private void showListViewLocations()
	{
		final ArrayList<Location> locations = databaseLocation.getDatabaseAsArrayList();
		listAdapterLocations = new ListAdapterLocations(this, locations);
		
		listViewLocations.setAdapter(listAdapterLocations);
		
		//TODO default, locationCurrent, create, maintain
		
		listViewLocations.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			View viewPrevious;
			int
					colorHighlight
					= getResources().getColor(R.color.common_google_signin_btn_text_light_default);
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				if (viewPrevious != null)
				{
					viewPrevious.setBackgroundColor(Color.TRANSPARENT);
				}
				
				viewPrevious = view;
				view.setBackgroundColor(colorHighlight);
				locationCurrent = locations.get(position);
			}
		});
	}
	
	public void onClickButtonAdd(View view)
	{
		setStateAddLocation();
	}
	
	public void onClickButtonCancel(View view)
	{
		formReset();
		setStateHome();
	}
	
	public void onClickButtonConfirm(View view)
	{
		String name    = textInputName.getText().toString();
		String address = textInputAddress.getText().toString();
		String siviso  = spinnerSiViSo.getSelectedItem().toString();
		
		databaseLocation.addData(name, address, SiViSo.fromString(siviso));
		setStateHome();
		showListViewLocations();
	}
	
	public void onClickButtonDelete(View view)
	{
		databaseLocation.delete(locationCurrent);
		listAdapterLocations.remove(locationCurrent);
	}
	
	private void formReset()
	{
		textInputName.getText().clear();
		textInputAddress.getText().clear();
		spinnerSiViSo.setSelection(0);
	}
	
	private void setStateAddLocation()
	{
		title.setText(R.string.title_add_location);
		switchOnOff.setVisibility(View.GONE);
		form.setVisibility(View.VISIBLE);
		buttonAdd.setVisibility(View.GONE);
		buttonDelete.setVisibility(View.GONE);
		buttonEdit.setVisibility(View.GONE);
		listViewLocations.setVisibility(View.GONE);
	}
	
	private void setStateHome()
	{
		title.setText(R.string.title_home);
		switchOnOff.setVisibility(View.VISIBLE);
		form.setVisibility(View.GONE);
		buttonDelete.setVisibility(View.VISIBLE);
		buttonEdit.setVisibility(View.VISIBLE);
		buttonAdd.setVisibility(View.VISIBLE);
		listViewLocations.setVisibility(View.VISIBLE);
	}
	
	//https://youtu.be/qS1E-Vrk60E?t=711
	@RequiresApi(api = Build.VERSION_CODES.M)
	private void makeMapFollowCurrentLocation()
	{
		if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED ||
		    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED)
		{
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER,
					0,
					0,
					new LocationListenerCurrentLocation(this)
			);
		} else
		{
			ActivityCompat.requestPermissions(this, new String[]
					                                  {Manifest.permission.ACCESS_FINE_LOCATION},
			                                  REQUEST_LOCATION_PERMISSION
			);
			return;
		}
	}
	
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		this.googleMap = googleMap;
	}
	
	
}
