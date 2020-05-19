package com.betcher.jordan.exampleservice.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.betcher.jordan.exampleservice.Main;
import com.betcher.jordan.exampleservice.activities.Home;

import java.util.Calendar;

public class Siviso extends JobIntentService
{
	private static final String TAG = "Siviso";
	
	private LocationListener listener;
	private LocationManager locationManager;
	Notification notification;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		createNotification("0");
		
		addLocationListener();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.d(TAG, "onStartCommand: ");
		super.onStartCommand(intent, flags, startId);
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if (locationManager != null)
		{
			locationManager.removeUpdates(listener);
		}
	}
	
	@Override
	protected void onHandleWork(@NonNull Intent intent)
	{
	
	}
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	@SuppressLint("MissingPermission")
	public void addLocationListener()
	{
		
		listener = new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				String message = "onLocationChanged: Seconds: " +
				                 Calendar.getInstance().getTime().getSeconds();
				
				Log.d(TAG, message
				      );
				createNotification(message);
			}
			
			@Override
			public void onStatusChanged(String s, int i, Bundle bundle)
			{
			
			}
			
			@Override
			public void onProviderEnabled(String s)
			{
			
			}
			
			@Override
			public void onProviderDisabled(String s)
			{
				Intent intentProviderSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				intentProviderSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intentProviderSettings);
			}
		};
		locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
	}
	
	public void createNotification(String input)
	{
		Intent notificationIntent = new Intent(this, Home.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
		                                                        0, notificationIntent, 0);
		notification = new NotificationCompat.Builder(this,
		                                              Main.NOTIFICATION_CHANNEL_ID)
				.setContentTitle("Title")
				.setContentText(input)
				.setContentIntent(pendingIntent)
				.build();
		startForeground(1, notification);
	}
}
