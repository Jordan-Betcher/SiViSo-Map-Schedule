package com.betcher.jordan.examplesqlitesavewithroom.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
	TextView textLatitude;
	TextView textLongitude;
	TextInputEditText inputName;
	Spinner spinnerSiviso;
	RecyclerView recyclerViewSiviso;
	
	private SivisoModel sivisoModel;
	
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
		
		CreateDataForTextLatitudeAndTextLongitude.run(textLatitude, textLongitude);
		
		final SivisoAdapter adapter = new SivisoAdapter();
		recyclerViewSiviso.setAdapter(adapter);
		
		sivisoModel = ViewModelProviders.of(this).get(SivisoModel.class);
		sivisoModel.getAllSivisoData().observe(this, new Observer<List<SivisoData>>()
		{
			@Override
			public void onChanged(@Nullable List<SivisoData> sivisoDatas)
			{
				adapter.setSivisoDatas(sivisoDatas);
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
	
	}
	
	public void onClickButtonEdit(View view)
	{
	
	}
}
