package com.betcher.jordan.examplegoogleplaceapi;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivitySearch extends AppCompatActivity
{
	
	EditText editTextAddress;
	ListView listViewAddresses;
	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> addresses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_basic_search);
		
		editTextAddress = (EditText) findViewById(R.id.editTextAddress);
		listViewAddresses = (ListView) findViewById(R.id.listViewAddresses);
		
		
		addresses = new ArrayList<String>();
		addresses.add("123 beda dako se");
		addresses.add("test dgie askkd alif");
		addresses.add("test 21 dgie askkd alif");
		addresses.add("test 32 dgie askkd alif");
		addresses.add("test 41 dgie askkd alif");
		
		
		arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addresses);
		addresses.add("test");
		listViewAddresses.setAdapter(arrayAdapter);
	}
	
	public void Search(View view)
	{
		String searchString = editTextAddress.getText().toString();
		
		List<Address> possibleAddresses = geoLocate(searchString);
		
		/*

		for(Address address : possibleAddresses)
		{
			String addressFull = "";
			
			for(int index = 0; index < address.getMaxAddressLineIndex(); index++)
			{
				addressFull += address.getAddressLine(index);
			}
			
			addresses.add(addressFull);
		}//*/
		
		addresses.add("tester address");
		arrayAdapter.notifyDataSetChanged();
		
		
		Toast.makeText(this, possibleAddresses.size(), Toast.LENGTH_SHORT).show();
		//Toast.makeText(this, searchString, Toast.LENGTH_SHORT).show();
	}
	
	public List<Address> geoLocate(String searchString)
	{
		List<Address> possibleAddresses = new ArrayList<>();
		
		
		try
		{
			Geocoder geocoder = new Geocoder(this);
			possibleAddresses = geocoder.getFromLocationName(searchString, 10);
		}
		catch (IOException e)
		{
			Log.e("Custom Error", "geoLocate: IOException: " + e.getMessage());
		}
		
		return possibleAddresses;
	}
}
