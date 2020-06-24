package com.jordan.betcher.sivisoLite;

import android.graphics.Color;

/*
 * Time is in Miliseconds
 * Distance is in Meters
 */
public class Defaults
{
	public static final int REQUEST_LOCATION_PERMISSION = 1;
	
	public static final long SECOND = 1000;
	public static final long MINUTE = 60 * SECOND;
	
	public static final long MAP_REQUEST_TIME_MIN = 1 * MINUTE;
	public static final float MAP_ZOOM = 17f;
	
	public static final double HOME_RADIUS = 70;
	public static final int HOME_FILL_COLOR = Color.argb(70, 50, 50, 50);
	public static final float HOME_STROKE_WIDTH = 1f;
	public static final int HOME_STROKE_COLOR = Color.BLACK;
	
	public static final int CARD_HIGHLIGHT_COLOR = Color.LTGRAY;
	public static final int CARD_NORMAL_COLOR = Color.WHITE;
	
	public static final String NOTIFICATION_CHANNEL_ID = "sivisoLiteNotificationChannel";
	public static final CharSequence NOTIFICATION_CHANNEL_NAME = "Siviso Lite";
	
	public static final long SERVICE_MIN_CHECK_TIME = 3 * SECOND;
	public static final float SERVICE_MIN_CHECK_DISTANCE = 5;
}
