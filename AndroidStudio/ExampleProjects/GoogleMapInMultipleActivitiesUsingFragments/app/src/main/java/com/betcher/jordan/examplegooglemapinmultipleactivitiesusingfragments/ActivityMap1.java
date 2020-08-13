package com.betcher.jordan.examplegooglemapinmultipleactivitiesusingfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityMap1 extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_fragment);
	}
	
	public void onClickGoToFragement2(View view)
	{
		Intent intent = new Intent(this, ActivityMap2.class);
		startActivity(intent);
	}
}
