package com.jordan.betcher.sivisoLite.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.activities.Home;

public class SivisoService extends Service
{
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		createNotification("Siviso");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		super.onStartCommand(intent, flags, startId);
		createNotification("Siviso");
		
		//SharedPreferences prefs = this.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		//prefs.edit().putBoolean(Defaults.PREFERENCE_KEY_IS_SERVICE_RUNNING, true).apply();
		
		return START_NOT_STICKY;
	}
	
	public void createNotification(String input)
	{
		Intent notificationIntent = new Intent(this, Home.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
		                                                        0, notificationIntent, 0);
		Notification notification = new NotificationCompat.Builder(this,
		                                                           Defaults.NOTIFICATION_CHANNEL_ID)
		.setContentTitle("Title Test")
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
		
		PreferencesForSivisoLite.setIsServiceRunning(this, false);
	}
}
