package com.betcher.jordan.sivisomapschedule;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textfield.TextInputEditText;

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
	TextInputEditText textInputLocation;
	Button            buttonCancel;
	Button            buttonConfirm;
	Button   buttonAdd;
	ListView locationsListView;
	Button buttonDelete;
	Button buttonEdit;
	
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
		textInputName     = (TextInputEditText) this.findViewById(R.id.textInputName);
		spinnerSiViSo     = (Spinner) this.findViewById(R.id.spinnerSiViSo);
		textInputLocation = (TextInputEditText) this.findViewById(R.id.textInputLocation);
		locationsListView = (ListView) this.findViewById(R.id.listViewLocations);
		buttonAdd         = (Button) this.findViewById(R.id.buttonAddLocation);
		buttonCancel      = (Button) this.findViewById(R.id.buttonCancel);
		buttonConfirm     = (Button) this.findViewById(R.id.buttonConfirm);
		buttonEdit        = (Button) this.findViewById(R.id.buttonEdit);
		buttonDelete      = (Button) this.findViewById(R.id.buttonDelete);
		
		makeMapFollowCurrentLocation();
		
		setStateHome();
		
	}
	
	public void onClickButtonAdd(View view)
	{
		setStateAddLocation();
	}
	
	private void setStateAddLocation()
	{
		title.setText(R.string.title_add_location);
		switchOnOff.setVisibility(View.GONE);
		form.setVisibility(View.VISIBLE);
		buttonAdd.setVisibility(View.GONE);
		buttonEdit.setVisibility(View.GONE);
		buttonDelete.setVisibility(View.GONE);
		locationsListView.setVisibility(View.GONE);
	}
	
	public void onClickButtonCancel(View view)
	{
		setStateHome();
	}
	
	private void setStateHome()
	{
		title.setText(R.string.title_home);
		switchOnOff.setVisibility(View.VISIBLE);
		form.setVisibility(View.GONE);
		buttonAdd.setVisibility(View.VISIBLE);
		buttonEdit.setVisibility(View.VISIBLE);
		buttonDelete.setVisibility(View.VISIBLE);
		locationsListView.setVisibility(View.VISIBLE);
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
		}
		else
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
