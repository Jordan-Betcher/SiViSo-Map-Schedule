package com.betcher.jordan.sivisomapschedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityFormAddress extends AppCompatActivity implements OnMapReadyCallback
{
	
	public GoogleMap googleMap;
	SupportMapFragment mapFragment;
	
	TextInputEditText textInputName;
	Spinner           spinnerSiViSo;
	
	Button buttonCancel;
	Button buttonAdd;
	
	SQLiteLocation databaseLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_form_map);
		mapFragment
				= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.formMap);
		mapFragment.getMapAsync(this);
		
		textInputName     = (TextInputEditText) this.findViewById(R.id.textViewName);
		spinnerSiViSo     = (Spinner) this.findViewById(R.id.spinnerSiViSo);
		buttonAdd         = (Button) this.findViewById(R.id.buttonAdd);
		buttonCancel      = (Button) this.findViewById(R.id.buttonCancel);
		
		//ButtonAdd isn't enabled until a spot on the map is selected
		buttonAdd.setEnabled(false);
	}
	
	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onMapReady(GoogleMap initGoogleMap)
	{
		this.googleMap = initGoogleMap;
		this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		sendMapToCurrentLocation();
		
		this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
		{
			@Override
			public void onMapClick(LatLng latLng)
			{
				
				setSivisoCircle(latLng);
				
			}
		});
		
	}
	
	
	Circle sivisoCircle = null;
	
	public void setSivisoCircle(LatLng latLng)
	{
		String latLngString = "";
		latLngString += "Latitude: ";
		latLngString += latLng.latitude;
		latLngString += "\n";
		latLngString += "Longitude: ";
		latLngString += latLng.longitude;
		
		if (sivisoCircle == null)
		{
		
		}
		else
		{
			sivisoCircle.remove();
		}
		
		sivisoCircle = googleMap.addCircle(new CircleOptions().center(latLng)
		                                                 .radius(70)
		                                                 .strokeColor(Color.RED)
		                                                 .fillColor(Color.argb(70, 150, 50, 50))
		                                                 .strokeWidth(4f)
		)
		;
		
		buttonAdd.setEnabled(true);
	}
	
	@RequiresApi(api = Build.VERSION_CODES.M)
	private void sendMapToCurrentLocation()
	{
		if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED ||
		    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED)
		{
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			Location
			                loc
			                                = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			LatLng          currentLocation = new LatLng(loc.getLatitude(), loc.getLongitude());
			this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f));
		} else
		{
			ActivityCompat.requestPermissions(this, new String[]
					                                  {Manifest.permission.ACCESS_FINE_LOCATION},
			                                  MapsActivity.REQUEST_LOCATION_PERMISSION
			);
			return;
		}
	}
	
	public void onClickButtonCancel(View view)
	{
		goToActivityHome();
	}
	
	public void onClickButtonAdd(View view)
	{
		String name = textInputName.getText().toString().trim();
		//Get LatLng
		String siviso = spinnerSiViSo.getSelectedItem().toString();
		
		Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
		
		if(name.equals(""))
		{
			textInputName.setError("The siviso area needs a name.");
		}
		else if(name.equals("default"))
		{
			textInputName.setError("Default is reserved, please use another name.");
		}
		else
		{
			//LatLng markedLocation =
			
			//databaseLocation.addData(name, address, SiViSo.fromString(siviso));
			goToActivityHome();
		}
	}
	
	public void goToActivityHome()
	{
		finish();
	}
}
