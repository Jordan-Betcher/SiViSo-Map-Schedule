package com.jordan.betcher.sivisoLite.service;

import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.jordan.betcher.sivisoLite.Defaults;
import com.jordan.betcher.sivisoLite.PreferencesForSivisoLite;
import com.jordan.betcher.sivisoLite.siviso.Siviso;

import static android.content.Context.AUDIO_SERVICE;

class SivisoCollision implements LocationListener
{
	SivisoService context;
	LastLocation lastLocation = LastLocation.NONE;
	AudioManager audioManager;
	int ringerModeBeforeAnyCollision;
	
	public SivisoCollision(SivisoService context)
	{
		this.context = context;
		this.audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
		this.ringerModeBeforeAnyCollision = audioManager.getRingerMode();
	}
	
	@Override
	public void onLocationChanged(Location location)
	{
		LatLng latLngHome = PreferencesForSivisoLite.getHomeLatLng(
		context);
		Location locationHome = new Location("");
		locationHome.setLatitude(latLngHome.latitude);
		locationHome.setLongitude(latLngHome.longitude);
		
		double distance = location.distanceTo(locationHome);
		
		if(distance <= Defaults.HOME_RADIUS)
		{
			if(LastLocation.HOME == lastLocation)
			{
				return;
			}
			else
			{
				lastLocation = LastLocation.HOME;
				Siviso siviso = PreferencesForSivisoLite
				.getHomeSiviso(context);
				setRingtone(siviso);
			}
		}
		else
		{
			if(LastLocation.DEFAULT == lastLocation)
			{
				return;
			}
			else
			{
				lastLocation = LastLocation.DEFAULT;
				Siviso siviso = PreferencesForSivisoLite
				.getDefaultSiviso(context);
				setRingtone(siviso);
			}
		}
	}
	
	private void setRingtone(Siviso siviso)
	{
		programmerFeedback("Setting Sivso: " + siviso.toString());
		if(Siviso.NONE == siviso)
		{
			audioManager
			.setRingerMode(ringerModeBeforeAnyCollision);
		}
		if(Siviso.SILENT == siviso)
		{
			audioManager
			.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		if(Siviso.VIBRATE == siviso)
		{
			audioManager
			.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		if(Siviso.SOUND == siviso)
		{
			audioManager
			.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
	}
	
	private void programmerFeedback(final String message)
	{
		//Log.d(TAG, message);
		
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable()
		{
			
			@Override
			public void run()
			{
				Toast
				.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	public void onStatusChanged(
	String provider, int status, Bundle extras)
	{
	
	}
	
	@Override
	public void onProviderEnabled(String provider)
	{
	
	}
	
	@Override
	public void onProviderDisabled(String provider)
	{
	
	}
	
	private enum LastLocation
	{
		NONE,
		HOME,
		DEFAULT;
	}
	
}
