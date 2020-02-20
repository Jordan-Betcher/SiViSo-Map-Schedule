package com.betcher.jordan.examplecustomlistviewfromlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<Location> locations = createDummyLocations();
		
	}
	
	private ArrayList<Location> createDummyLocations()
	{
		ArrayList<Location> locations = new ArrayList<Location>();
		
		Location location1 = new Location ("Home", "West Florida", SiViSo.SOUND);
		Location location2 = new Location ("Store", "41st Blvd", SiViSo.SOUND);
		Location location3 = new Location ("Store", "East India", SiViSo.SOUND);
		Location location4 = new Location ("Emergancy", "Fire Station", SiViSo.SILENT);
		Location location5 = new Location ("Emergancy", "Hostpital", SiViSo.SILENT);
		Location location6 = new Location ("Emergancy", "Police", SiViSo.SILENT);
		Location location7 = new Location ("Work", "Holi Wood", SiViSo.VIBRATE);
		
		locations.add(location1);
		locations.add(location2);
		locations.add(location3);
		locations.add(location4);
		locations.add(location5);
		locations.add(location6);
		locations.add(location7);
		
		return locations;
	}
}
