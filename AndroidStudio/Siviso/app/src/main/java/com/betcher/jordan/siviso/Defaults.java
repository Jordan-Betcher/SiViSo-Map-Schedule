package com.betcher.jordan.siviso;

import android.graphics.Color;

public class Defaults
{
	public static final float HOME_ZOOM = 14f;
	
	public static final float SIVISO_ZOOM = 17f;
	public static final double SIVISO_RADIUS = 70;
	public static final float SIVISO_STROKE_WIDTH = 1f;
	public static final int SIVISO_STROKE_COLOR = Color.TRANSPARENT;
	
	//second in milliseconds
	public static final long SECOND = 1000;
	public static final long SERVICE_MIN_TIME = 10*SECOND;
	
	public static final String DATABASE_NAME = "Siviso";
	public static final String EXTRA_NAME_ID = "id";
	public static final String EXTRA_NAME_NAME = "name";
	public static final String EXTRA_NAME_SIVISO = "siviso";
	public static final String EXTRA_NAME_LATITUDE = "latitude";
	public static final String EXTRA_NAME_LONGITUDE = "longitude";
	
	public static final int ITEM_SELECT_HIGHLIGHT_COLOR = Color.LTGRAY;
	
	public static final int REQUEST_LOCATION_PERMISSION = 1;
	
	public static final String NOTIFICATION_CHANNEL_ID = "SivisoChannelID";
	public static final String NOTIFICATION_CHANNEL_NAME = "Siviso";
	public static final String DEFAULT_SIVISO_NAME = "Default";
	
	public static final int SIVISO_COLOR_NONE = Color.argb(120, 255, 255, 255);
	public static final int SIVISO_COLOR_SILENT = Color.argb(200, 99, 106, 133);
	public static final int SIVISO_COLOR_VIBRATE = Color.argb(200, 131, 199, 167);
	public static final int SIVISO_COLOR_SOUND = Color.argb(200, 182, 250, 150);
	
	public static final int PERMISSIONS_OPEN_SIVISO_ACCEPTED_COLOR = Color.argb(255, 0, 150, 0);
	public static final int PERMISSIONS_OPEN_SIVISO_NOT_ACCEPTED_COLOR = Color.argb(255, 200, 0, 0);
}
