package com.jordan.betcher.sivisoLite;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class Main extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		createNotificationChannel();
	}
	
	private void createNotificationChannel()
	{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
		{
			NotificationManager manager = getSystemService(
			NotificationManager.class);
			if(manager.getNotificationChannel(
			Defaults.NOTIFICATION_CHANNEL_ID) == null)
			{
				NotificationChannel serviceChannel = new NotificationChannel(
				Defaults.NOTIFICATION_CHANNEL_ID,
				Defaults.NOTIFICATION_CHANNEL_NAME,
				NotificationManager.IMPORTANCE_DEFAULT
				);
				manager.createNotificationChannel(serviceChannel);
			}
		}
	}
}
