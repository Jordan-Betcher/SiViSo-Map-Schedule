package com.jordan.betcher.siviso.livedatafrompreferences;

import android.graphics.Color;

import java.util.HashMap;

public class Defaults
{
	public static final Ringmode HomeRingmode = Ringmode.Sound;
	
	public static final HashMap<Ringmode, Integer> ringmodeColors = createRingmodeColorFromDefaults();
	
	private static HashMap<Ringmode, Integer> createRingmodeColorFromDefaults()
	{
		HashMap<Ringmode, Integer> ringmodeColor = new HashMap<Ringmode, Integer>();
		
		ringmodeColor.put(Ringmode.None, Defaults.RingmodeNoneColor);
		ringmodeColor.put(Ringmode.Silent, Defaults.RingmodeSilentColor);
		ringmodeColor.put(Ringmode.Vibrate, Defaults.RingmodeVibrateColor);
		ringmodeColor.put(Ringmode.Sound, Defaults.RingmodeSoundColor);
		
		return ringmodeColor;
	}
	
	private static final int RingmodeNoneColor = Color.argb(120, 255, 255, 255);
	private static final int RingmodeSilentColor = Color.argb(200, 99, 106, 133);
	private static final int RingmodeVibrateColor = Color.argb(200, 131, 199, 167);
	private static final int RingmodeSoundColor = Color.argb(200, 182, 250, 150);
}
