package com.betcher.jordan.examplecustomlistviewfromlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

	ListView listViewLocations;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listViewLocations = findViewById(R.id.listViewLocations);
		
		ArrayList<Location> locations = createDummyLocations();
		
		ListAdapterLocations listAdapterLocations = new ListAdapterLocations(this, locations);
		listViewLocations.setAdapter(listAdapterLocations);
		listViewLocations.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast toast = Toast.makeText(getApplicationContext(), "initListView: " + position, Toast. LENGTH_SHORT);
				toast.show();
			}
		});
		
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
