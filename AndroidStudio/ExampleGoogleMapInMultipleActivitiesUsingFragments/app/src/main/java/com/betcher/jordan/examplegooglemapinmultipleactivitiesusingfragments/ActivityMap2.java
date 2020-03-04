package com.betcher.jordan.examplegooglemapinmultipleactivitiesusingfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMap2 extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_fragment2);
	}
	
	public void onClickGoToFragement1(View view)
	{
		Intent intent = new Intent(this, ActivityMap1.class);
		startActivity(intent);
	}
}
