package com.jordan.betcher.siviso.livedatafrompreferences;

import android.os.Bundle;
import android.widget.AdapterView;
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
		
		Spinner ringmodeHome = findViewById(R.id.spinner_homeRingmode);
		int spinnerLayout = android.R.layout.simple_spinner_dropdown_item;
		ringmodeHome.setAdapter(new ArrayAdapter<Ringmode>(this, spinnerLayout, Ringmode.values()));
		ringmodeHome.setOnItemSelectedListener(
		new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(
			AdapterView<?> parent, android.view.View view,
			int position, long id)
			{
				model.updateHomeRingmode(Ringmode.values()[position]);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			
			}
		});
		
		TextView ringmode = findViewById(R.id.textView_ringmode);
		Model model = new Model();
		LiveData<Home> homeLiveData = model.home();
		homeLiveData.observe(this, new ObserverTextViewRingmode(ringmode));
	}
}