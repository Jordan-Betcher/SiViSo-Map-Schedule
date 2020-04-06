package com.betcher.jordan.examplegooglemaplocationpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityHome extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}
	
	public void onClickButtonGoToLayoutPicker(View view)
	{
		Intent IntentGoToLayoutPicker = new Intent(this, ActivityLocationPicker.class);
		startActivity(IntentGoToLayoutPicker);
	}
	
	
	public void onClickButtonGoToMapClick(View view)
	{
		Intent IntentGoToMapClick = new Intent(this, ActivityMapClick.class);
		startActivity(IntentGoToMapClick);
	}
}
