package com.betcher.jordan.siviso.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoRepository;

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
	private String previousSiviso = "None";
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
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(Defaults.PREFERENCE_KEY_IS_SERVICE_RUNNING, true).apply();
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
			HashMap<String, ArrayList<Double>> collidedSivisos = new HashMap<>(3);
			collidedSivisos.put("Silent", new ArrayList<Double>());
			collidedSivisos.put("Vibrate", new ArrayList<Double>());
			collidedSivisos.put("Sound", new ArrayList<Double>());
			
			double distance_closestSiviso = -1;
			
			//get closest Service_ManageRingMode that isn't none and sort sivisos that current location is in
			for(SivisoData sivisoData : sivisoDatas)
			{
				if(sivisoData.getSiviso().equals("None"))
				{
					continue;
				}
				
				Location sivisoLocation = new Location("");
				sivisoLocation.setLatitude(sivisoData.getLatitude());
				sivisoLocation.setLongitude(sivisoData.getLongitude());
				double distance = currentLocation.distanceTo(sivisoLocation);
				
				if(distance < Defaults.SIVISO_RADIUS)
				{
					Log.d(TAG, "onLocationChanged: " + sivisoData.getSiviso());
					ArrayList<Double> distances = collidedSivisos.get(sivisoData.getSiviso());
					distances.add(distance);
				}
				
				if(distance < distance_closestSiviso || distance_closestSiviso == -1)
				{
					distance_closestSiviso = distance;
				}
			}
			
			if(collidedSivisos.get("Silent").size() > 0)
			{
				if(previousSiviso.equals("None"))
				{
					None_SaveRingMode();
				}
				
				if(!previousSiviso.equals("Silent"))
				{
					programmerFeedback("Silent");
					previousSiviso = "Silent";
					
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}
				
				Update(collidedSivisos.get("Silent"));
			}
			else if(collidedSivisos.get("Vibrate").size() > 0)
			{
				if(previousSiviso.equals("None"))
				{
					None_SaveRingMode();
				}
				
				if(!previousSiviso.equals("Vibrate"))
				{
					programmerFeedback("Vibrate");
					previousSiviso = "Vibrate";
					
					audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
				}
				
				Update(collidedSivisos.get("Vibrate"));
			}
			else if(collidedSivisos.get("Sound").size() > 0)
			{
				if(previousSiviso.equals("None"))
				{
					None_SaveRingMode();
				}
				
				if(!previousSiviso.equals("Sound"))
				{
					programmerFeedback("Sound");
					previousSiviso = "Sound";
					
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				}
				
				Update(collidedSivisos.get("Sound"));
			}
			else
			{
				if(!previousSiviso.equals("None"))
				{
					programmerFeedback("None");
					previousSiviso = "None";
					
					RevertToRingMode_None();
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
	
	private void RevertToRingMode_None()
	{
		//String defaultSiviso = Preferences_Siviso.DefaultSiviso()
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		String defaultSiviso = prefs.getString(Defaults.PREFERENCE_KEY_DEFAULT_SIVISO, "None");
		
		if(defaultSiviso.equals("None"))
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
		else
		{
			ArrayList<String> newMode = new ArrayList<>();
			newMode.add(defaultSiviso);
		}
	}
	
	private void None_SaveRingMode()
	{
		noneRingMode = audioManager.getRingerMode();
	}
	
	private ArrayList<String> getPossibleSiviso(ArrayList<SivisoData> collidedSivisoData)
	{
		ArrayList<String> possibleSiviso = new ArrayList<>();
		for (SivisoData sivisoData : collidedSivisoData)
		{
			possibleSiviso.add(sivisoData.getSiviso());
		}
		return possibleSiviso;
	}
	
	private ArrayList<SivisoData> getSivisoThatCollideWithCurrentLocation(List<SivisoData> sivisoDatas, Location currentLocation)
	{
		ArrayList<SivisoData> sivisos = new ArrayList<>();
		
		for(SivisoData sivisoData : sivisoDatas)
		{
			Location sivisoLocation = new Location("");
			sivisoLocation.setLatitude(sivisoData.getLatitude());
			sivisoLocation.setLongitude(sivisoData.getLongitude());
			
			double distance = currentLocation.distanceTo(sivisoLocation);
			
			if(distance < Defaults.SIVISO_RADIUS)
			{
				sivisos.add(sivisoData);
			}
		}
		
		return sivisos;
	}
	
	private void programmerFeedback(final String message)
	{
		Log.d(TAG, message);
		
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable()
		{
			
			@Override
			public void run()
			{
				Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		});
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
		previousSiviso = "None";
		start();
	}
	
	public void stop()
	{
		if (locationManager != null)
		{
			locationManager.removeUpdates(this);
		}
		
		SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(Defaults.PREFERENCE_KEY_IS_SERVICE_RUNNING, false).apply();
	}
}
