package com.betcher.jordan.exampleservice.activities;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
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
	
	private static final String PREFS_NAME = "com.betcher.jordan.exampleservice.activities.preferences";
	
	Switch switchOffOn;
	
	Siviso sivisoService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		switchOffOn = findViewById(R.id.switchOffOn);
		
		runPermissions();
		
		
		sivisoService = new Siviso();
		SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs.getBoolean("isServiceRunning", false);
		
		if(isServiceRunning)
		{
			startSivisoService();
			switchOffOn.setChecked(true);
		}
		
		Log.d(TAG, "onCreate: " + isServiceRunning);
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
		
		NotificationManager notificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
		    && !notificationManager.isNotificationPolicyAccessGranted()) {
			
			Intent intent = new Intent(
					android.provider.Settings
							.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
			
			startActivity(intent);
		}
	}
	
	public void onOffOnClicked(View view)
	{
		if (switchOffOn.isChecked())
		{
			startSivisoService();
		}
		else
		{
			stopSivisoService();
		}
		
	}
	
	private void startSivisoService()
	{
		Log.d(TAG, "startSivisoActivity: Start");
		Intent startSivisoService = new Intent(this, Siviso.class);
		ContextCompat.startForegroundService(this, startSivisoService);
		
		SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean("isServiceRunning", true).apply();
	}
	
	private void stopSivisoService()
	{
		Log.d(TAG, "stopSivisoActivity: Stop");
		stopService(new Intent(this, Siviso.class));
		
		SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean("isServiceRunning", false).apply();
	}
}
