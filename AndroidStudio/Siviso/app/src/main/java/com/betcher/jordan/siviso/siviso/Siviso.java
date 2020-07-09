package com.betcher.jordan.siviso.siviso;

import com.betcher.jordan.siviso.Defaults;

import java.util.HashMap;

public enum Siviso
{
	None(Defaults.SIVISO_COLOR_NONE),
	Silent(Defaults.SIVISO_COLOR_SILENT),
	Vibrate(Defaults.SIVISO_COLOR_VIBRATE),
	Sound(Defaults.SIVISO_COLOR_SOUND);
	
	int color;
	Siviso(int color)
	{
		this.color = color;
	}
	
	static Siviso[] sivisos = Siviso.values();
	
	static HashMap<String, Integer> nameIndex = createNameIndex();
	private static HashMap<String, Integer> createNameIndex()
	{
		HashMap<String, Integer> nameIndex = new HashMap<>();
		
		for(int index = 0; index < sivisos.length; index++)
		{
			Siviso siviso = sivisos[index];
			String name = siviso.name();
			nameIndex.put(name, index);
		}
		
		return nameIndex;
	}
	
	public static int color(String sivisoName)
	{
		return sivisos[nameIndex.get(sivisoName)].color;
	}
	
	public static int index(String name)
	{
		return nameIndex.get(name);
	}
	
	public static Siviso siviso(String sivisoName)
	{
		return sivisos[nameIndex.get(sivisoName)];
	}
}
