package com.betcher.jordan.exampleservice;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Main extends Application
{
	public static final String NOTIFICATION_CHANNEL_ID = "sivisoServiceChannel";
	public static final String NOTIFICATION_CHANNEL_NAME = "Siviso Service Channel";
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		createNotificationChannel();
	}
	
	private void createNotificationChannel()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel serviceChannel = new NotificationChannel(
					NOTIFICATION_CHANNEL_ID,
					NOTIFICATION_CHANNEL_NAME,
					NotificationManager.IMPORTANCE_DEFAULT
			);
			NotificationManager manager = getSystemService(NotificationManager.class);
			manager.createNotificationChannel(serviceChannel);
		}
	}
}
