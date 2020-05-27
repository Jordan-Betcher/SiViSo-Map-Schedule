package com.betcher.jordan.siviso.service;

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

import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoRepository;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.AUDIO_SERVICE;

class LocationListenerSetSiviso implements LocationListener
{
	private static final String TAG = "LocationListenerSetSivi";
	AudioManager audioManager;
	Service context;
	
	SivisoRepository sivisoRepository;
	
	public LocationListenerSetSiviso(Siviso context)
	{
		this.context = context;
		this.audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
		sivisoRepository = SivisoRepository.getInstance(context.getApplicationContext());
	}
	
	@Override
	public void onLocationChanged(Location currentLocation)
	{
		List<SivisoData> sivisoDatas = new ArrayList<>();
		
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
			Log.d(TAG, "onLocationChanged: Working SivisoModel.getAllSivisoData().getValue() ");
			
			sivisoDatas = sivisoRepository.getAllSivisoData().getValue();
			for(SivisoData sivisoData : sivisoDatas)
			{
			
			}
			
			String showMessage = "The number of sivisoDatas: " + sivisoDatas.size();
			programmerFeedback(showMessage);
			
			//*/
		}
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
}
