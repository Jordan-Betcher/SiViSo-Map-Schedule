package com.betcher.jordan.siviso;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public class Defaults
{
	public static final LatLng HOME_LATLNG = new LatLng(47.797649,-122.2117209);
	public static final float HOME_ZOOM = 14f;
	
	public static final float SIVISO_ZOOM = 17f;
	public static final double SIVISO_RADIUS = 70;
	public static final float SIVISO_STROKE_WIDTH = 1f;
	public static final int SIVISO_STROKE_COLOR = Color.BLACK;
	public static final HashMap<String, Integer> SIVISO_TO_COLOR = createSivisoToIntegerHashMap();
	private static HashMap<String, Integer> createSivisoToIntegerHashMap()
	{
		HashMap<String, Integer> sivisoToInteger = new HashMap<>();
		sivisoToInteger.put("None", Color.argb(120, 0, 0, 0));
		sivisoToInteger.put("Silent", Color.argb(120, 0, 255, 0));
		sivisoToInteger.put("Vibrate", Color.argb(120, 255, 255, 0));
		sivisoToInteger.put("Sound", Color.argb(120, 255, 0, 0));
		
		return sivisoToInteger;
	}
	
	public static final String DATABASE_NAME = "Siviso";
	public static final String EXTRA_NAME_ID = "id";
	public static final String EXTRA_NAME_NAME = "name";
	public static final String EXTRA_NAME_SIVISO = "siviso";
	public static final String EXTRA_NAME_LATITUDE = "latitude";
	public static final String EXTRA_NAME_LONGITUDE = "longitude";
	
	public static final int ITEM_SELECT_HIGHLIGHT_COLOR = Color.LTGRAY;
	
	public static final String PREFERENCE_NAME = "com.betcher.jordan.exampleservice.activities.preferences";
	
	public static final int REQUEST_LOCATION_PERMISSION = 1;
	
	public static final String NOTIFICATION_CHANNEL_ID = "SivisoChannelID";
	public static final String NOTIFICATION_CHANNEL_NAME = "Siviso";
	public static final String PREFERENCE_KEY_DEFAULT_SIVISO = "DefaultSiviso";
	public static final String DEFAULT_SIVISO_NAME = "Default";
	public static final String PREFERENCE_KEY_IS_SERVICE_RUNNING = "isServiceRunning";
}
