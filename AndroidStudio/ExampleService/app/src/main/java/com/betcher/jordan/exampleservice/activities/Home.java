package com.betcher.jordan.exampleservice.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.betcher.jordan.exampleservice.R;
import com.betcher.jordan.exampleservice.service.Siviso;

public class Home extends AppCompatActivity
{
	private static final String TAG = "Home";
	public static final int REQUEST_LOCATION_PERMISSION = 1;
	
	Switch switchOffOn;
	
	Siviso sivisoService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		switchOffOn = findViewById(R.id.switchOffOn);
		
		runPermissions();
		
		
		sivisoService = Siviso.getInstance();
	}
	
	private void runPermissions()
	{
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
		    PackageManager.PERMISSION_GRANTED &&
		    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
		    PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(this, new String[]
					                                  {Manifest.permission.ACCESS_FINE_LOCATION},
			                                  REQUEST_LOCATION_PERMISSION
			                                 );
		}
	}
	
	public void onOffOnClicked(View view)
	{
		if(switchOffOn.isChecked())
		{
			startSivisoActivity();
		}
		else
		{
			stopSivisoActivity();
		}
		
	}
	
	private void startSivisoActivity()
	{
		Log.d(TAG, "startSivisoActivity: Start");
		Intent startSivisoService = new Intent(this, Siviso.class);
		ContextCompat.startForegroundService(this, startSivisoService);
	}
	
	private void stopSivisoActivity()
	{
		Log.d(TAG, "stopSivisoActivity: Stop");
		stopService(new Intent(this, Siviso.class));
	}
}
