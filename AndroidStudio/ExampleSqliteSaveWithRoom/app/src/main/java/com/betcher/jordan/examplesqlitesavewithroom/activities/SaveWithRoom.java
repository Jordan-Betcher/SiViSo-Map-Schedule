package com.betcher.jordan.examplesqlitesavewithroom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.betcher.jordan.examplesqlitesavewithroom.R;
import com.betcher.jordan.examplesqlitesavewithroom.actions.CreateDataForTextLatitudeAndTextLongitude;

import java.util.Random;

public class SaveWithRoom extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_with_room);
		
		TextView textLatitude = findViewById(R.id.textLatitiude);
		TextView textLongitude = findViewById(R.id.textLongitude);
		CreateDataForTextLatitudeAndTextLongitude.run(textLatitude, textLongitude);
	}
}
