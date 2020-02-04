package com.betcher.jordan.sivisomapschedule;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
	private static final int REQUEST_LOCATION_PERMISSION = 1;
	
	private GoogleMap mMap;
	SupportMapFragment mapFragment;
	
	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
		if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED ||
		    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
		    PackageManager.PERMISSION_GRANTED)
		{
			// TODO: Consider calling
			//    Activity#requestPermissions
			// here to request the missing permissions, and then overriding
			//			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//			//                                          int[] grantResults)
			//			// to handle the case where the user grants the permission. See the documentation
			//			// for Activity#requestPermissions for more details.
			
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER,
					0,
					0,
					new LocationListener()
					{
						@Override
						public void onLocationChanged(Location location)
						{
							double latitude = location.getLatitude();
							double longitude = location.getLongitude();
							LatLng currentLocation = new LatLng(latitude, longitude);
							mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
							mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));
						}
						
						@Override
						public void onStatusChanged(String provider, int status, Bundle extras)
						{
						
						}
						
						@Override
						public void onProviderEnabled(String provider)
						{
						
						}
						
						@Override
						public void onProviderDisabled(String provider)
						{
						
						}
					}
			);
		}
		else
		{
			ActivityCompat.requestPermissions(this, new String[]
				                                   {Manifest.permission.ACCESS_FINE_LOCATION},
		                                   REQUEST_LOCATION_PERMISSION);
			return;
		}
	}
	
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
	}
	
	public void onClick(View view)
	{
		mapFragment.getView().setVisibility(View.VISIBLE);
	}
	
	
}
