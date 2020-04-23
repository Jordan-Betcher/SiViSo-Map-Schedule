package com.betcher.jordan.sivisomapschedule;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.betcher.jordan.sivisomapschedule.SivisoLocation.SivisoLocationsListViewWrapper;
import com.betcher.jordan.sivisomapschedule.SivisoLocation.SivisoLocationsSelectOnItemClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
	public static final int REQUEST_LOCATION_PERMISSION = 1;
	
	public GoogleMap googleMap;
	SupportMapFragment mapFragment;
	
	TextView          title;
	Switch            switchOnOff;
	Button            buttonAddLocation;
	Button            buttonDelete;
	Button            buttonEdit;
	ListView          listViewLocations;
	
	SQLiteLocation databaseLocation;
	//ListAdapterLocations listAdapterLocations;
	
	SivisoLocationsListViewWrapper           sivisoLocationsListViewWrapper;
	SivisoLocationsSelectOnItemClickListener locationSelectItemClickListener;
	
	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.homeMap);
		mapFragment.getMapAsync(this);
		
		title             = (TextView) this.findViewById(R.id.textViewTitle);
		switchOnOff       = (Switch) this.findViewById(R.id.switchOnOff);
		listViewLocations = (ListView) this.findViewById(R.id.listViewLocations);
		buttonAddLocation = (Button) this.findViewById(R.id.buttonAddLocation);
		buttonDelete      = (Button) this.findViewById(R.id.buttonDelete);
		buttonEdit        = (Button) this.findViewById(R.id.buttonEdit);
		
		databaseLocation = new SQLiteLocation(this);
		
		sivisoLocationsListViewWrapper  = new SivisoLocationsListViewWrapper(
				this,
				databaseLocation,
				listViewLocations
		);
		locationSelectItemClickListener = new SivisoLocationsSelectOnItemClickListener(
				this,
				sivisoLocationsListViewWrapper, buttonDelete, buttonEdit
		);
		locationSelectItemClickListener.addOnLocationSelectedListener(new DeleteButtonSivisoLocationSelectListener(this, buttonDelete));
		listViewLocations.setOnItemClickListener(locationSelectItemClickListener);
		
	}
	
	//https://stackoverflow.com/questions/4186021/how-to-start-new-activity-on-button-click
	public void onClickButtonAddLocation(View view)
	{
		Intent myIntent = new Intent(this, ActivityFormAddress.class);
		startActivity(myIntent);
	}
	
	/* When you start working on Edit Address Activity
	public void OnClickButtonEdit(View view)
	{
		Intent myIntent = new Intent(this, ActivityEditAddress.class);
		
		if(locationSelectItemClickListener.getSivisoLocationSelected() != null)
		{
			myIntent.putExtra("selectedId", locationSelectItemClickListener.getSivisoLocationSelected().getId());
		}
		else
		{
			myIntent.putExtra("selectedId", "");
		}
		
		startActivity(myIntent);
	}
	//*/
	
	
	public void onClickButtonDelete(View view)
	{
		databaseLocation.delete(locationSelectItemClickListener.getSivisoLocationSelected());
		sivisoLocationsListViewWrapper.refresh();
	}
	
	public void onClickButtonEdit(View view)
	{
		//Send to ActivtyFormEdit
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
					new MapListenerGoToCurrentLocation(this)
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
	public void onResume()
	{  // After a pause OR at startup
		super.onResume();
		sivisoLocationsListViewWrapper.refresh();
	}
	
	public void onMapReady(GoogleMap googleMap)
	{
		this.googleMap = googleMap;
	}
	
	
}
