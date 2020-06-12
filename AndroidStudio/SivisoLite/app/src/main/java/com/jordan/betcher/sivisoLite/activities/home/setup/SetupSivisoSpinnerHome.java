package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.siviso.Siviso;

public class SetupSivisoSpinnerHome
{
	public static void run(Home home, Spinner spinnerHome)
	{
		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(
						home,
						android.R.layout.simple_spinner_item,
						Siviso.getArrayList());
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		spinnerHome.setAdapter(adapter);
		
		spinnerHome.setSelection(PreferencesForSivisoLite.getHomeSiviso(home));
	}
}
