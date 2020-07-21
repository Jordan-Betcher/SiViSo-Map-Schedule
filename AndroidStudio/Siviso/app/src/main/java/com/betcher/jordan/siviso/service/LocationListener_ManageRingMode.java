package com.betcher.jordan.siviso.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoRepository;
import com.betcher.jordan.siviso.siviso.Siviso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.AUDIO_SERVICE;

class LocationListener_ManageRingMode implements LocationListener
{
	private static final String TAG = "LocationListenerSetSivi";
	Service context;
	LocationManager locationManager;
	AudioManager audioManager;
	
	SivisoRepository sivisoRepository;
	private Siviso previousSiviso = Siviso.None;
	private int noneRingMode = -1;
	
	public LocationListener_ManageRingMode(
	Service_ManageRingMode context)
	{
		this.context = context;
		this.audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
		sivisoRepository = SivisoRepository.getInstance(context.getApplicationContext());
		
		locationManager = (LocationManager) context
		.getApplicationContext()
		.getSystemService(Context.LOCATION_SERVICE);
		
		Preferences_Siviso.saveIsServiceRunning(context, true);
	}
	
	@SuppressLint("MissingPermission")
	@Override
	public void onLocationChanged(Location currentLocation)
	{
		if(sivisoRepository == null)
		{
			Log.d(TAG, "onLocationChanged: SivisoModel was null");
			return;
		}
		else if (sivisoRepository.getAllSivisoData() == null)
		{
			Log.d(TAG, "onLocationChanged: SivisoModel.getAllSivisoData() was null");
			return;
		}
		else if (sivisoRepository.getAllSivisoData().getValue() == null)
		{
			Log.d(TAG, "onLocationChanged: SivisoModel.getAllSivisoData().getValue() was null");
			return;
		}
		else
		{
			List<SivisoData> sivisoDatas = sivisoRepository.getAllSivisoData().getValue();
			HashMap<Siviso, ArrayList<Double>> collidedSivisos = new HashMap<>(3);
			collidedSivisos.put(Siviso.Silent, new ArrayList<Double>());
			collidedSivisos.put(Siviso.Vibrate, new ArrayList<Double>());
			collidedSivisos.put(Siviso.Sound, new ArrayList<Double>());
			
			double distance_closestSiviso = -1;
			
			//get closest Service_ManageRingMode that isn't none and sort sivisos that current location is in
			for(SivisoData sivisoData : sivisoDatas)
			{
				if(sivisoData.siviso().equals(Siviso.None))
				{
					continue;
				}
				
				Location sivisoLocation = new Location("");
				sivisoLocation.setLatitude(sivisoData.latitude());
				sivisoLocation.setLongitude(sivisoData.longitude());
				double distance = currentLocation.distanceTo(sivisoLocation);
				
				if(distance < Defaults.SIVISO_RADIUS)
				{
					ArrayList<Double> distances = collidedSivisos.get(sivisoData.siviso());
					distances.add(distance);
				}
				
				if(distance < distance_closestSiviso || distance_closestSiviso == -1)
				{
					distance_closestSiviso = distance;
				}
			}
			
			if(collidedSivisos.get(Siviso.Silent).size() > 0)
			{
				ManageRingMode(Siviso.Silent);
				
				Update(collidedSivisos.get(Siviso.Silent));
			}
			else if(collidedSivisos.get(Siviso.Vibrate).size() > 0)
			{
				ManageRingMode(Siviso.Vibrate);
				
				Update(collidedSivisos.get(Siviso.Vibrate));
			}
			else if(collidedSivisos.get(Siviso.Sound).size() > 0)
			{
				ManageRingMode(Siviso.Sound);
				
				Update(collidedSivisos.get(Siviso.Sound));
			}
			else
			{
				ManageRingMode(Siviso.None);
				
				locationManager
				.requestLocationUpdates
				(
				LocationManager.GPS_PROVIDER,
				Defaults.SERVICE_MIN_TIME,
				(float) distance_closestSiviso,
				this
				);
			}
		}
	}
	
	@SuppressLint("MissingPermission")
	private void Update(ArrayList<Double> distances_Siviso)
	{
		double distance_closestSiviso = Defaults.SIVISO_RADIUS;
		
		for(double distance : distances_Siviso)
		{
			if(distance < distance_closestSiviso)
			{
				distance_closestSiviso = distance;
			}
		}
		
		locationManager
		.requestLocationUpdates
		(
		LocationManager.GPS_PROVIDER,
		Defaults.SERVICE_MIN_TIME,
		(float) distance_closestSiviso,
		this
		);
	}
	
	private void ManageRingMode(Siviso siviso)
	{
		if(previousSiviso.equals(siviso))
		{
			return;
		}
		
		
		if(previousSiviso.equals(Siviso.None))
		{
			noneRingMode = audioManager.getRingerMode();
		}
		
		
		if(siviso.equals(Siviso.None))
		{
			ManageDefaultRingMode();
		}
		else if(siviso.equals(Siviso.Silent))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		else if(siviso.equals(Siviso.Vibrate))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		else if(siviso.equals(Siviso.Sound))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		
		previousSiviso = siviso;
	}
	
	private void ManageDefaultRingMode()
	{
		Siviso defaultSiviso = Preferences_Siviso.defaultSiviso(context);
		
		if(defaultSiviso.equals(previousSiviso))
		{
			return;
		}
		
		if(defaultSiviso.equals(Siviso.None))
		{
			if(noneRingMode == -1)
			{
				//Do nothing
			}
			else
			{
				audioManager.setRingerMode(noneRingMode);
			}
		}
		else if(defaultSiviso.equals(Siviso.Silent))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		else if(defaultSiviso.equals(Siviso.Vibrate))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		else if(defaultSiviso.equals(Siviso.Sound))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
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
	
	
	@SuppressLint("MissingPermission")
	public void start()
	{
		if(locationManager != null)
		{
			locationManager
			.requestSingleUpdate(LocationManager.GPS_PROVIDER,
			                     this, null);
		}
	}
	
	public void refresh()
	{
		previousSiviso = Siviso.None;
		start();
	}
	
	public void stop()
	{
		if (locationManager != null)
		{
			locationManager.removeUpdates(this);
		}
		
		Preferences_Siviso.saveIsServiceRunning(context, false);
	}
}
