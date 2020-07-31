package com.betcher.jordan.siviso.siviso;

import com.betcher.jordan.siviso.Defaults;

import java.util.HashMap;

public enum SivisoRingmode
{
	None(Defaults.SIVISO_COLOR_NONE),
	Silent(Defaults.SIVISO_COLOR_SILENT),
	Vibrate(Defaults.SIVISO_COLOR_VIBRATE),
	Sound(Defaults.SIVISO_COLOR_SOUND);
	
	int color;
	SivisoRingmode(int color)
	{
		this.color = color;
	}
	
	static SivisoRingmode[] sivisoRingmodes = SivisoRingmode.values();
	
	static HashMap<String, Integer> nameIndex = createNameIndex();
	private static HashMap<String, Integer> createNameIndex()
	{
		HashMap<String, Integer> nameIndex = new HashMap<>();
		
		for(int index = 0; index < sivisoRingmodes.length; index++)
		{
			SivisoRingmode sivisoRingmode = sivisoRingmodes[index];
			String name = sivisoRingmode.name();
			nameIndex.put(name, index);
		}
		
		return nameIndex;
	}
	
	public static int color(String sivisoName)
	{
		return sivisoRingmodes[nameIndex.get(sivisoName)].color;
	}
	
	public static int index(String name)
	{
		return nameIndex.get(name);
	}
	
	public static SivisoRingmode siviso(String sivisoName)
	{
		return sivisoRingmodes[nameIndex.get(sivisoName)];
	}
	
	public static int Count()
	{
		return sivisoRingmodes.length;
	}
	
	public static SivisoRingmode siviso(int index)
	{
		return sivisoRingmodes[index];
	}
	
	public static int color(int index)
	{
		return sivisoRingmodes[index].color();
	}
	
	public static String name(int index)
	{
		return sivisoRingmodes[index].name();
	}
	
	public int index()
	{
		return nameIndex.get(name());
	}
	
	public int color()
	{
		return color;
	}
}
