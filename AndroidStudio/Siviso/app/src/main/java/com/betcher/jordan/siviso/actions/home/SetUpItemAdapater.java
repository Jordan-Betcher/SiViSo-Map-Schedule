package com.betcher.jordan.siviso.actions.home;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.activities.Home;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;

import java.util.List;

public class SetUpItemAdapater
{
	public static ItemAdapter run(Home home,
	                              SivisoModel sivisoModel,
	                              RecyclerView recyclerViewSiviso)
	{
		final ItemAdapter itemAdapter = new ItemAdapter();
		recyclerViewSiviso.setAdapter(itemAdapter);
		
		sivisoModel.getAllSivisoData().observe(home, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				itemAdapter.setSivisoDatas(sivisoDatas);
			}
		});
		
		return itemAdapter;
	}
}
