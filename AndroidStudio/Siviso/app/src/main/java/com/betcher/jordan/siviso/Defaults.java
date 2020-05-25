package com.betcher.jordan.siviso;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;

public class Defaults
{
	public static final LatLng HOME_LATLNG = new LatLng(47.797649,-122.2117209);
	public static final float HOME_ZOOM = 14f;
	
	public static final float SIVISO_ZOOM = 17f;
	public static final double SIVISO_RADIUS = 70;
	public static final int SIVISO_FILL_COLOR = Color.argb(70, 50, 50, 50);
	public static final float SIVISO_STROKE_WIDTH = 1f;
	public static final int SIVISO_STROKE_COLOR = Color.BLACK;
	
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
}
