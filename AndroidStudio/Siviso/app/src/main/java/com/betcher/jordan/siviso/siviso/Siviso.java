package com.betcher.jordan.siviso.siviso;

import android.graphics.Color;

import java.util.HashMap;

public enum Siviso
{
	None(Color.argb(120, 255, 255, 255)),
	Silent(Color.argb(120, 255, 255, 255)),
	Vibrate(Color.argb(120, 255, 255, 255)),
	Sound(Color.argb(120, 255, 255, 255));
	
	int color;
	Siviso(int color)
	{
		this.color = color;
	}
	
	static Siviso[] sivisos = Siviso.values();
	static HashMap<Siviso, Integer> sivisoIndex = createSivisoIndex();
	private static HashMap<Siviso, Integer> createSivisoIndex()
	{
		HashMap<Siviso, Integer> sivisoIndex = new HashMap<>();
		
		for(int index = 0; index < sivisos.length; index++)
		{
			Siviso siviso = sivisos[index];
			sivisoIndex.put(siviso, index);
		}
		
		return sivisoIndex;
	}
	
	
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
	
	public int getIndex(Siviso siviso)
	{
		return sivisoIndex.get(siviso);
	}
	
	public static int getIndex(String name)
	{
		return nameIndex.get(name);
	}
}
