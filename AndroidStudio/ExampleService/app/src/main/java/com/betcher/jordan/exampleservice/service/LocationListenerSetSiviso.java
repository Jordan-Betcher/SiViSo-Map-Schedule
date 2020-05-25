package com.betcher.jordan.exampleservice.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.betcher.jordan.exampleservice.Main;
import com.betcher.jordan.exampleservice.activities.Home;

import static android.content.Context.AUDIO_SERVICE;

public class LocationListenerSetSiviso implements LocationListener
{
	private static final String TAG = "LocationListenerSetSivi";
	AudioManager audioManager;
	Service context;
	
	Notification notification;
	
	public LocationListenerSetSiviso(Service context)
	{
		this.context = context;
		this.audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
	}
	
	@Override
	public void onLocationChanged(Location location)
	{
		//LatLng point = new LatLng(37.6354, -122.0224);
		Location point = new Location("");//provider name is unnecessary
		point.setLatitude(37.6354d);
		point.setLongitude(-122.0224d);
		
		double distance = location.distanceTo(point);
		String message = "";
		
		if(distance > 10000)
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			message = "Vibrate";
		}
		else
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			message = "Ringer";
		}
		
		programmerFeedback(message);
		createNotification(message);
	}
	
	
	
	private void programmerFeedback(final String message)
	{
		Log.d(TAG, message);
		
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void createNotification(String input)
	{
		Intent notificationIntent = new Intent(context, Home.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context,
		                                                        0, notificationIntent, 0);
		notification = new NotificationCompat.Builder(context,
		                                              Main.NOTIFICATION_CHANNEL_ID)
				.setContentTitle("Title")
				.setContentText(input)
				.setContentIntent(pendingIntent)
				.build();
		context.startForeground(1, notification);
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
		context.startActivity(intentProviderSettings);
	}
}
