package com.betcher.jordan.examplesqlitedatabasecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
	
	TextView textViewDatabase;
	SQLiteLocation databaseLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textViewDatabase = (TextView) findViewById(R.id.textViewDatabase);
		databaseLocation = new SQLiteLocation(this);
		
		displayDatabase();
	}
	
	public void addTestToDatabase(View view)
	{
		boolean work = databaseLocation.addData("test", "address 12345", SiViSo.SILENT);
		displayDatabase();
	}
	
	public void deleteTestFromDatabase(View view)
	{
		int rowsDeleted = databaseLocation.delete("test");
		Toast.makeText(this, "It deleted " + rowsDeleted, Toast.LENGTH_LONG).show();
		displayDatabase();
	}
	
	public void updateTestToVibrate(View view)
	{
		//databaseLocation.update("test", SiViSo.VIBRATE);
		HashMap<Location, Integer> locationIds = databaseLocation.getDatabaseAsArrayList();
		Integer id = (Integer)locationIds.values().toArray()[0];
		databaseLocation.update(id, new Location("new name", "new address", SiViSo.SOUND));
		displayDatabase();
	}
	
	private void displayDatabase()
	{
		HashMap<Location, Integer> locationIds = databaseLocation.getDatabaseAsArrayList();
		Set<Location> locations = locationIds.keySet();
		
		String display = "";
		
		for(Location location : locations)
		{
			display += locationIds.get(location);
			display += " ";
			display += location.getName();
			display += " ";
			display += location.getAddress();
			display += " ";
			display += location.getSiviso().name;
			display += "\n";
		}
		
		textViewDatabase.setText(display);
	}
}
