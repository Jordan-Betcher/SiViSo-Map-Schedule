package com.jordan.betcher.sivisoLite.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.activities.Home;

public class SivisoService extends Service
{
	SivisoCollision sivisoCollision;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		createNotification("Siviso");
		sivisoCollision = new SivisoCollision(this);
		sivisoCollision.start();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		//Have to create a new sivisoCollision in order to force a rerun, maybe bc of lastlocation?
		sivisoCollision.stop();
		sivisoCollision = new SivisoCollision(this);
		sivisoCollision.start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void createNotification(String input)
	{
		Intent notificationIntent = new Intent(this, Home.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
		                                                        0, notificationIntent, 0);
		Notification notification = new NotificationCompat.Builder(this,
		                                                           Defaults.NOTIFICATION_CHANNEL_ID)
		.setContentTitle("Siviso Lite Start")
		.setContentText(input)
		.setContentIntent(pendingIntent)
		.build();
		
		this.startForeground(1, notification);
	}
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		
		sivisoCollision.stop();
		
		PreferencesForSivisoLite.setIsServiceRunning(this, false);
	}
}
