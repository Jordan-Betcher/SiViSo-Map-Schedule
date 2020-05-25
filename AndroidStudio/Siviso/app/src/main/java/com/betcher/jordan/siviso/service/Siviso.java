package com.betcher.jordan.siviso.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.Home;

public class Siviso extends Service
{
	private static final String TAG = "Siviso";
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		super.onStartCommand(intent, flags, startId);
		
		return START_NOT_STICKY;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		createNotification("test");
		
		Log.d(TAG, "onCreate: ");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "onDestroy: ");
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
