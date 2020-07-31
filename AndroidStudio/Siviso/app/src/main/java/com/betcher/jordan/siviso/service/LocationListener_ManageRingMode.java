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
import com.betcher.jordan.siviso.database.SivisoDatabase;
import com.betcher.jordan.siviso.database.TableRow_Siviso;
import com.betcher.jordan.siviso.siviso.SivisoRingmode;

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
	
	SivisoDatabase sivisoDatabase;
	private SivisoRingmode previousSivisoRingmode = SivisoRingmode.None;
	private int noneRingMode = -1;
	
	public LocationListener_ManageRingMode(
	Service_ManageRingMode context)
	{
		this.context = context;
		this.audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
		sivisoDatabase = new SivisoDatabase(context.getApplication());
		
		locationManager = (LocationManager) context
		.getApplicationContext()
		.getSystemService(Context.LOCATION_SERVICE);
		
		Preferences_Siviso.saveIsServiceRunning(context, true);
	}
	
	@SuppressLint("MissingPermission")
	@Override
	public void onLocationChanged(Location currentLocation)
	{
		if(sivisoDatabase == null)
		{
			Log.d(TAG, "onLocationChanged: SivisoDatabase was null");
			return;
		}
		else if (sivisoDatabase.getAllSivisoData() == null)
		{
			Log.d(TAG, "onLocationChanged: SivisoDatabase.getAllSivisoData() was null");
			return;
		}
		else if (sivisoDatabase.getAllSivisoData().getValue() == null)
		{
			Log.d(TAG, "onLocationChanged: SivisoDatabase.getAllSivisoData().getValue() was null");
			return;
		}
		else
		{
			List<TableRow_Siviso> sivisoDatas = sivisoDatabase.getAllSivisoData().getValue();
			HashMap<SivisoRingmode, ArrayList<Double>> collidedSivisos = new HashMap<>(3);
			collidedSivisos.put(SivisoRingmode.Silent, new ArrayList<Double>());
			collidedSivisos.put(SivisoRingmode.Vibrate, new ArrayList<Double>());
			collidedSivisos.put(SivisoRingmode.Sound, new ArrayList<Double>());
			
			double distance_closestSiviso = -1;
			
			//get closest Service_ManageRingMode that isn't none and sort sivisos that current location is in
			for(TableRow_Siviso sivisoData : sivisoDatas)
			{
				if(sivisoData.siviso().equals(SivisoRingmode.None))
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
			
			if(collidedSivisos.get(SivisoRingmode.Silent).size() > 0)
			{
				ManageRingMode(SivisoRingmode.Silent);
				
				Update(collidedSivisos.get(SivisoRingmode.Silent));
			}
			else if(collidedSivisos.get(SivisoRingmode.Vibrate).size() > 0)
			{
				ManageRingMode(SivisoRingmode.Vibrate);
				
				Update(collidedSivisos.get(SivisoRingmode.Vibrate));
			}
			else if(collidedSivisos.get(SivisoRingmode.Sound).size() > 0)
			{
				ManageRingMode(SivisoRingmode.Sound);
				
				Update(collidedSivisos.get(SivisoRingmode.Sound));
			}
			else
			{
				ManageRingMode(SivisoRingmode.None);
				
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
	
	private void ManageRingMode(SivisoRingmode sivisoRingmode)
	{
		if(previousSivisoRingmode.equals(sivisoRingmode))
		{
			return;
		}
		
		
		if(previousSivisoRingmode.equals(SivisoRingmode.None))
		{
			noneRingMode = audioManager.getRingerMode();
		}
		
		
		if(sivisoRingmode.equals(SivisoRingmode.None))
		{
			ManageDefaultRingMode();
		}
		else if(sivisoRingmode.equals(SivisoRingmode.Silent))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		else if(sivisoRingmode.equals(SivisoRingmode.Vibrate))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		else if(sivisoRingmode.equals(SivisoRingmode.Sound))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		
		previousSivisoRingmode = sivisoRingmode;
	}
	
	private void ManageDefaultRingMode()
	{
		SivisoRingmode defaultSivisoRingmode = Preferences_Siviso.defaultSiviso(context);
		
		if(defaultSivisoRingmode.equals(previousSivisoRingmode))
		{
			return;
		}
		
		if(defaultSivisoRingmode.equals(SivisoRingmode.None))
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
		else if(defaultSivisoRingmode.equals(SivisoRingmode.Silent))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		else if(defaultSivisoRingmode.equals(SivisoRingmode.Vibrate))
		{
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		else if(defaultSivisoRingmode.equals(SivisoRingmode.Sound))
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
		previousSivisoRingmode = SivisoRingmode.None;
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
