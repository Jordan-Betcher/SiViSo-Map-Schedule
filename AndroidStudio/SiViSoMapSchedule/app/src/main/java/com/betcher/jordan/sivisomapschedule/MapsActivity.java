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
	Button            buttonAdd;
	Button            buttonAddLocation;
	Button            buttonDelete;
	Button            buttonEdit;
	Button            buttonConfirmEdit;
	Button            buttonCancelEdit;
	ListView          listViewLocations;
	
	SQLiteLocation       databaseLocation;
	//ListAdapterLocations listAdapterLocations;
	
	HandlerListViewLocations handlerListViewLocations;
	
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
		form              = (ConstraintLayout) this.findViewById(R.id.formAdd);
		textInputName     = (TextInputEditText) this.findViewById(R.id.textViewName);
		spinnerSiViSo     = (Spinner) this.findViewById(R.id.spinnerSiViSo);
		textInputAddress  = (TextInputEditText) this.findViewById(R.id.textInputAddress);
		listViewLocations = (ListView) this.findViewById(R.id.listViewLocations);
		buttonAddLocation = (Button) this.findViewById(R.id.buttonAddLocation);
		buttonCancel      = (Button) this.findViewById(R.id.buttonCancel);
		buttonDelete      = (Button) this.findViewById(R.id.buttonDelete);
		buttonEdit        = (Button) this.findViewById(R.id.buttonEdit);
		buttonAdd         = (Button) this.findViewById(R.id.buttonAdd);
		buttonConfirmEdit = (Button) this.findViewById(R.id.buttonComfirmEdit);
		buttonCancelEdit  = (Button) this.findViewById(R.id.buttonCancelEdit);
		
		databaseLocation = new SQLiteLocation(this);
		
		makeMapFollowCurrentLocation();
		
		handlerListViewLocations = new HandlerListViewLocations(this, databaseLocation, listViewLocations);
		
		setStateHome();
	}
	
	
	
	public void onClickButtonAddLocation(View view)
	{
		formReset();
		setStateAddLocation();
	}
	
	public void onClickButtonCancel(View view)
	{
		setStateHome();
	}
	
	public void onClickButtonCancelEdit(View view)
	{
		setStateHome();
	}
	
	public void onClickButtonConfirmEdit(View view)
	{
		formReset();
		setStateHome();
	}
	
	public void onClickButtonAdd(View view)
	{
		String name    = textInputName.getText().toString();
		String address = textInputAddress.getText().toString();
		String siviso  = spinnerSiViSo.getSelectedItem().toString();
		
		databaseLocation.addData(name, address, SiViSo.fromString(siviso));
		setStateHome();
		handlerListViewLocations.refresh();
	}
	
	public void onClickButtonDelete(View view)
	{
		databaseLocation.delete(handlerListViewLocations.getLocationSelected());
		handlerListViewLocations.refresh();
	}
	
	public void onClickButtonEdit(View view)
	{
		setStateEditLocation();
		Location locationSelected = handlerListViewLocations.getLocationSelected();
		textInputName.setText(locationSelected.getName());
		textInputAddress.setText(locationSelected.getAddress());
		spinnerSiViSo.setSelection(SiViSo.indexOf(locationSelected.getSiviso()));
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
		buttonAddLocation.setVisibility(View.GONE);
		buttonDelete.setVisibility(View.GONE);
		buttonEdit.setVisibility(View.GONE);
		listViewLocations.setVisibility(View.GONE);
		
		buttonCancel.setVisibility(View.VISIBLE);
		buttonAdd.setVisibility(View.VISIBLE);
		buttonConfirmEdit.setVisibility(View.GONE);
		buttonCancelEdit.setVisibility(View.GONE);
	}
	
	private void setStateEditLocation()
	{
		title.setText(R.string.title_add_location);
		switchOnOff.setVisibility(View.GONE);
		form.setVisibility(View.VISIBLE);
		buttonAddLocation.setVisibility(View.GONE);
		buttonDelete.setVisibility(View.GONE);
		buttonEdit.setVisibility(View.GONE);
		listViewLocations.setVisibility(View.GONE);
		
		buttonCancel.setVisibility(View.GONE);
		buttonAdd.setVisibility(View.GONE);
		buttonConfirmEdit.setVisibility(View.VISIBLE);
		buttonCancelEdit.setVisibility(View.VISIBLE);
	}
	
	private void setStateHome()
	{
		title.setText(R.string.title_home);
		switchOnOff.setVisibility(View.VISIBLE);
		form.setVisibility(View.GONE);
		buttonDelete.setVisibility(View.VISIBLE);
		buttonEdit.setVisibility(View.VISIBLE);
		buttonAddLocation.setVisibility(View.VISIBLE);
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
