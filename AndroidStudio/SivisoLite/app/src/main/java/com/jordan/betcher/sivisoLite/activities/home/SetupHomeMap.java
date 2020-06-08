package com.jordan.betcher.sivisoLite.activities.home;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.Home;

public class SetupHomeMap
{
	public static void run(Home home)
	{
		SupportMapFragment mapFragment =
				(SupportMapFragment)
				home
				.getSupportFragmentManager()
				.findFragmentById(R.id.homeMap);
		
		mapFragment.getMapAsync(new Setup());
	}
	
	private static class Setup implements OnMapReadyCallback
	{
		@Override
		public void onMapReady(GoogleMap googleMap)
		{
		
		}
	}
}
