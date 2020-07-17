package com.betcher.jordan.siviso.activities.home.methods;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.activities.Home;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.betcher.jordan.siviso.siviso.Siviso;

import java.util.ArrayList;
import java.util.List;

public class SetUpItemAdapater
{
	public static ItemAdapter run(final Home home,
	                              SivisoModel sivisoModel,
	                              RecyclerView recyclerViewSiviso)
	{
		final ItemAdapter itemAdapter = new ItemAdapter(home, sivisoModel);
		recyclerViewSiviso.setAdapter(itemAdapter);
		
		sivisoModel.getAllSivisoData().observe(home, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				ArrayList<SivisoData> shown = new ArrayList<>();
				
				Siviso defaultSiviso = Preferences_Siviso.defaultSiviso(home);
				SivisoData defaultSivisoData = new SivisoData(Defaults.DEFAULT_SIVISO_NAME, defaultSiviso.name(), 0, 0);
				
				shown.add(defaultSivisoData);
				shown.addAll(sivisoDatas);
				
				itemAdapter.setSivisoDatas(shown);
			}
		});
		
		return itemAdapter;
	}
}
