package com.betcher.jordan.siviso;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class Main extends Application
{
	private static final String TAG = "Main";
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG, "onCreate: ");
		createNotificationChannel();
	}
	
	private void createNotificationChannel()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationManager manager = getSystemService(NotificationManager.class);
			if(manager.getNotificationChannel(Defaults.NOTIFICATION_CHANNEL_ID) == null)
			{
				Log.d(TAG, "createNotificationChannel: Created Channel");
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
