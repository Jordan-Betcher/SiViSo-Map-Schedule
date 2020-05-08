package com.betcher.jordan.siviso.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.home.SetMapHomePosition;
import com.betcher.jordan.siviso.actions.home.StartActivityAdd;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.EnableButton;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.onItemClickListener.SelectItem;
import com.betcher.jordan.siviso.activities.home.sivisoRecyclerView.ItemAdapter;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Home extends AppCompatActivity
{
	GoogleMap map;
	RecyclerView recyclerViewSiviso;
	ItemAdapter sivisoRecyclerViewItemAdapter;
	SivisoModel sivisoModel;
	
	Button buttonDelete;
	Button buttonEdit;
	
	SelectItem selectItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.homeMap);
		mapFragment.getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				map = googleMap;
				SetMapHomePosition.run(map);
			}
		});
		
		buttonDelete = findViewById(R.id.buttonDelete);
		buttonEdit = findViewById(R.id.buttonEdit);
		
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		recyclerViewSiviso.setLayoutManager(new LinearLayoutManager(this));
		
		sivisoRecyclerViewItemAdapter = new ItemAdapter();
		recyclerViewSiviso.setAdapter(sivisoRecyclerViewItemAdapter);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		sivisoModel.getAllSivisoData().observe(this, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				sivisoRecyclerViewItemAdapter.setSivisoDatas(sivisoDatas);
			}
		});
		
		selectItem = new SelectItem(sivisoRecyclerViewItemAdapter);
		sivisoRecyclerViewItemAdapter.addOnItemClickedListener(selectItem);
		sivisoRecyclerViewItemAdapter.addOnItemClickedListener(new EnableButton(buttonDelete));
		sivisoRecyclerViewItemAdapter.addOnItemClickedListener(new EnableButton(buttonEdit));
	}
	
	public void onClickButtonAdd(View view)
	{
		LatLng mapPosition = map.getCameraPosition().target;
		StartActivityAdd.run(this, mapPosition);
	}
	
	public void onClickButtonDelete(View view)
	{
		sivisoModel.delete(selectItem.getSelectedSiviso());
		
		selectItem.unselect();
		buttonDelete.setEnabled(false);
		buttonEdit.setEnabled(false);
	}
	
	public void onClickButtonEdit(View view)
	{
	
	}
}
