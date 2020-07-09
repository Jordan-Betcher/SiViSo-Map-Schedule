package com.betcher.jordan.siviso.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.activities.Home;

public class Siviso extends Service
{
	private static final String TAG = "Siviso";
	
	private LocationListenerSetSiviso listener;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		createNotification("Siviso");
		listener = new LocationListenerSetSiviso(this);
		listener.start();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		listener.refresh();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		listener.stop();
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
		
		this.startForeground(2, notification);
	}
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
