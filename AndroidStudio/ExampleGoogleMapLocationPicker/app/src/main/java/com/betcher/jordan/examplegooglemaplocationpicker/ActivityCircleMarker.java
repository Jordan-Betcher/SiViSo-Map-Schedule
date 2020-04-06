package com.betcher.jordan.examplegooglemaplocationpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityCircleMarker extends AppCompatActivity implements OnMapReadyCallback
{
	private GoogleMap mMap;
	private TextView textViewOutputLatitudeLongitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_marker);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
		textViewOutputLatitudeLongitude = (TextView) findViewById(R.id.textViewOutputLatitudeLongitude);
	}
	
	
	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
		
		// Add a marker in Sydney and move the camera
		LatLng sydney = new LatLng(-34, 151);
		mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
		
		mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
		{
			@Override
			public void onMapClick(LatLng latLng)
			{
				
				setSivisoMarker(latLng);
				
			}
		});
	}
	
	Circle sivisoMarker = null;
	
	public void setSivisoMarker(LatLng latLng)
	{
		String latLngString = "";
		latLngString += "Latitude: ";
		latLngString += latLng.latitude;
		latLngString += "\n";
		latLngString += "Longitude: ";
		latLngString += latLng.longitude;
		
		textViewOutputLatitudeLongitude.setText(latLngString);
		
		if (sivisoMarker == null)
		{
			sivisoMarker = mMap.addCircle(new CircleOptions().center(latLng)
			                                                 .radius(1000));
		}
		else
		{
			sivisoMarker.remove();
			sivisoMarker = mMap.addCircle(new CircleOptions().center(latLng)
			                                                 .radius(1000));
		}
	}
}
