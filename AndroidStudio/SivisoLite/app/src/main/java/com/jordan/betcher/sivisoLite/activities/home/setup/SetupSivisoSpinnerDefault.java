package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.siviso.Siviso;

import java.util.ArrayList;

public class SetupSivisoSpinnerDefault
{
	public static void run(Home home, Spinner spinnerDefault)
	{
		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(
						home,
						android.R.layout.simple_spinner_item,
						Siviso.getArrayList());
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		spinnerDefault.setAdapter(adapter);
		
		SharedPreferences prefs = home.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		int sivisoForDefault = prefs.getInt(Defaults.PREFERENCE_KEY_SPINNER_DEFAULT, Siviso.getPositionOf(Defaults.DEFAULT_FOR_SPINNER_DEFAULT));
		
		spinnerDefault.setSelection(sivisoForDefault);
	}
}
