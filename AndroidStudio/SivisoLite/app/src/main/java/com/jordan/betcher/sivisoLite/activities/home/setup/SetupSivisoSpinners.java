package com.jordan.betcher.sivisoLite.activities.home.setup;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.jordan.betcher.sivisoLite.R;
import com.jordan.betcher.sivisoLite.activities.Home;
import com.jordan.betcher.sivisoLite.siviso.Siviso;

import java.util.ArrayList;

public class SetupSivisoSpinners
{
	public static void run(Home home)
	{
		ArrayList<Spinner> spinners = new ArrayList<>();
		spinners.add((Spinner)home.findViewById(R.id.spinnerDefault));
		spinners.add((Spinner)home.findViewById(R.id.spinnerHome));
		
		for (Spinner spinner : spinners)
		{
			ArrayAdapter<String> adapter =
					new ArrayAdapter<String>(
							home,
			                android.R.layout.simple_spinner_item,
							Siviso.getArray());
			adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
			spinner.setAdapter(adapter);
		}
	}
}
