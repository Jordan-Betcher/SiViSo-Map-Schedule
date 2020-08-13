package com.betcher.jordan.examplegooglemaplocationpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
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
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		// Add a marker in Sydney and move the camera
		LatLng canyonHillsCommunityChurch = new LatLng(47.797649,-122.2117209);
		mMap.addMarker(new MarkerOptions().position(canyonHillsCommunityChurch).title("Canyon Hills Community Church"));
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(canyonHillsCommunityChurch, 17f));
		
		mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
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
		
		textViewOutputLatitudeLongitude.setText(latLngString);
		
		if (sivisoCircle == null)
		{
		
		}
		else
		{
			sivisoCircle.remove();
		}
		
		sivisoCircle = mMap.addCircle(new CircleOptions().center(latLng)
		                                                 .radius(70)
		                                                 .strokeColor(Color.RED)
		                                                 .fillColor(Color.argb(70, 150, 50, 50))
		                                                 .strokeWidth(4f)
		                             )
		;
	}
}
