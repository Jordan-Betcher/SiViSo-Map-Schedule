package com.betcher.jordan.siviso.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.Home;
import com.betcher.jordan.siviso.database.SivisoRepository;

public class Siviso extends Service
{
	private static final String TAG = "Siviso";
	
	private LocationListener listener;
	private LocationManager locationManager;
	SivisoRepository sivisoRepository;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG, "onCreate: ");
		addLocationListener();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		super.onStartCommand(intent, flags, startId);
		createNotification("Siviso");
		
		return START_NOT_STICKY;
	}
	
	@SuppressLint("MissingPermission")
	private void addLocationListener()
	{
		listener = new LocationListenerSetSiviso(this);
		locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "onDestroy: ");
		if (locationManager != null)
		{
			locationManager.removeUpdates(listener);
		}
	}
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	public void createNotification(String input)
	{
		Intent notificationIntent = new Intent(this, Home.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
		                                                        0, notificationIntent, 0);
		Notification notification = new NotificationCompat.Builder(this,
		                                                           Defaults.NOTIFICATION_CHANNEL_ID)
				.setContentTitle("Title")
				.setContentText(input)
				.setContentIntent(pendingIntent)
				.build();
		
		this.startForeground(2, notification);
	}
}
