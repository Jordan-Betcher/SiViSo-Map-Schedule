package com.betcher.jordan.siviso.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.actions.home.SetMapHomePosition;
import com.betcher.jordan.siviso.actions.home.StartActivityAdd;
import com.betcher.jordan.siviso.activities.home.SelectOnItemClickListener;
import com.betcher.jordan.siviso.activities.home.SivisoRecyclerViewItemAdapter;
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
	SivisoRecyclerViewItemAdapter sivisoRecyclerViewItemAdapter;
	SivisoModel sivisoModel;
	
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
		
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		recyclerViewSiviso.setLayoutManager(new LinearLayoutManager(this));
		
		sivisoRecyclerViewItemAdapter = new SivisoRecyclerViewItemAdapter();
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
		
		sivisoRecyclerViewItemAdapter.addOnItemClickedListener(new SelectOnItemClickListener(sivisoRecyclerViewItemAdapter));
	}
	
	public void onClickButtonAdd(View view)
	{
		LatLng mapPosition = map.getCameraPosition().target;
		StartActivityAdd.run(this, mapPosition);
	}
	
	public void onClickButtonDelete(View view)
	{
	
	}
	
	public void onClickButtonEdit(View view)
	{
	
	}
}
