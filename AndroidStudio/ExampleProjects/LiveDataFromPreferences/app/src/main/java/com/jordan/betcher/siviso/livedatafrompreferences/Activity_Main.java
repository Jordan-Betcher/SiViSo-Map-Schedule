package com.jordan.betcher.siviso.livedatafrompreferences;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

public class Activity_Main extends AppCompatActivity
{
	Model model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Model model = new Model();
		
		Spinner ringmodeHome = findViewById(R.id.spinner_homeRingmode);
		int spinnerLayout = android.R.layout.simple_spinner_dropdown_item;
		ringmodeHome.setAdapter(new ArrayAdapter<Ringmode>(this, spinnerLayout, Ringmode.values()));
		ringmodeHome.setOnItemSelectedListener(new UpdateHomeRingmode(model));
		
		TextView ringmode = findViewById(R.id.textView_ringmode);
		LiveData<Home> homeLiveData = model.home();
		homeLiveData.observe(this, new TextViewMatchRingmode(ringmode));
	}
}