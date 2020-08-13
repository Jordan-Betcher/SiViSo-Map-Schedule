package com.betcher.jordan.examplesqlitesavewithroom.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.betcher.jordan.examplesqlitesavewithroom.R;
import com.betcher.jordan.examplesqlitesavewithroom.actions.savewithroom.CreateDataForTextLatitudeAndTextLongitude;
import com.betcher.jordan.examplesqlitesavewithroom.database.SivisoData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SaveWithRoom extends AppCompatActivity
{
	private static final String TAG = "SaveWithRoom";
	
	TextView textLatitude;
	TextView textLongitude;
	TextInputEditText inputName;
	Spinner spinnerSiviso;
	RecyclerView recyclerViewSiviso;
	
	private SivisoModel sivisoModel;
	private SivisoAdapter sivisoAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_with_room);
		
		textLatitude = findViewById(R.id.textLatitiude);
		textLongitude = findViewById(R.id.textLongitude);
		inputName = findViewById(R.id.inputName);
		spinnerSiviso = findViewById(R.id.spinnerSiviso);
		recyclerViewSiviso = findViewById(R.id.recyclerViewSiviso);
		recyclerViewSiviso.setLayoutManager(new LinearLayoutManager(this));
		
		CreateDataForTextLatitudeAndTextLongitude.run(textLatitude, textLongitude);
		
		sivisoAdapter = new SivisoAdapter();
		recyclerViewSiviso.setAdapter(sivisoAdapter);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		sivisoModel.getAllSivisoData().observe(this, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				sivisoAdapter.setSivisoDatas(sivisoDatas);
				Log.d(TAG, "onChanged: " + sivisoDatas.size());
			}
		});
		
	}
	
	public void onClickButtonAdd(View view)
	{
		String name = inputName.getText().toString().trim();
		String siviso = spinnerSiviso.getSelectedItem().toString();
		double latitude = Double.parseDouble(textLatitude.getText().toString());
		double longitude = Double.parseDouble(textLongitude.getText().toString());
		
		SivisoData sivisoData = new SivisoData(name, siviso, latitude, longitude);
		sivisoModel.insert(sivisoData);
	}
	
	public void onClickButtonDelete(View view)
	{
		boolean isSelected = sivisoAdapter.getIsSivisoSelected();
		if(isSelected)
		{
			SivisoData selectedSiviso = sivisoAdapter.getSelectedSiviso();
			sivisoModel.delete(selectedSiviso);
		}
		
	}
	
	public void onClickButtonEdit(View view)
	{
		boolean isSelected = sivisoAdapter.getIsSivisoSelected();
		if(isSelected)
		{
			SivisoData selectedSiviso = sivisoAdapter.getSelectedSiviso();
			
			String name = inputName.getText().toString().trim();
			String siviso = spinnerSiviso.getSelectedItem().toString();
			double latitude = Double.parseDouble(textLatitude.getText().toString());
			double longitude = Double.parseDouble(textLongitude.getText().toString());
			
			SivisoData updatedSiviso = new SivisoData(name, siviso, latitude, longitude);
			updatedSiviso.setId(selectedSiviso.getId());
			
			sivisoModel.update(updatedSiviso);
		}
	}
}
