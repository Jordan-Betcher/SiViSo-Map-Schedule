package com.jordan.betcher.siviso.livedatafrompreferences;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Activity_Main extends AppCompatActivity
{

	View view;
	Model model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView longitude = findViewById(R.id.textView_Longitude);
		TextView latitude = findViewById(R.id.textView_Latitude);
		
		CardView rowDefault = findViewById(R.id.cardViewDefault);
		CardView rowHome = findViewById(R.id.cardViewHome);
		Spinner ringmodeDefault = findViewById(R.id.spinnerDefault);
		Spinner ringmodeHome = findViewById(R.id.spinnerHome);
		
		rowDefault.setOnClickListener(
		new android.view.View.OnClickListener(){
			@Override
			public void onClick(android.view.View v)
			{
				view.SelectDefault();
			}
		});
		
		rowHome.setOnClickListener(
		new android.view.View.OnClickListener(){
			@Override
			public void onClick(android.view.View v)
			{
				view.SelectHome();
			}
		});
		
		//ringmodeDefault.setAdapter(new SpinnerAdapterRingmodeColors(model));
		
		View view = new View();
		Model model = new Model();
	}
}