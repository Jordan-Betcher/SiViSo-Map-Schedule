package com.jordan.betcher.sivisoLite;

import com.jordan.betcher.sivisoLite.siviso.Siviso;

public class Defaults
{
	public static final String PREFERENCE_NAME = "SivisoLitePreferences";
	public static final String PREFERENCE_KEY_IS_SERVICE_RUNNING = "isServiceRunning";
	
	public static final String PREFERENCE_KEY_SPINNER_DEFAULT = "spinnerDefault";
	public static final Siviso DEFAULT_FOR_SPINNER_DEFAULT = Siviso.NONE;
	
	public static final String PREFERENCE_KEY_SPINNER_HOME = "spinnerHome";
	public static final Siviso DEFAULT_FOR_SPINNER_HOME = Siviso.VIBRATE;
	
	public static final int REQUEST_LOCATION_PERMISSION = 1;
	
	public static final long MINUTE = 1000;
	public static final long MAP_REQUEST_TIME_MIN = 1 * MINUTE;
	
	public static final float MAP_ZOOM = 17f;
}
