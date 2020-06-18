package com.jordan.betcher.sivisoLite.siviso;

import android.media.AudioManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public enum Siviso
{
	NONE("None"),
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound");
	
	private String name;
	private static ArrayList<String> sivisos = createArrayListOfSivisos();
	private static HashMap<String, Siviso> stringSiviso = createStringSivisoDictionary();
	
	private static HashMap<String, Siviso> createStringSivisoDictionary()
	{
		Siviso[] sivisoEnums = Siviso.values();
		HashMap<String, Siviso> stringSiviso = new HashMap<String, Siviso>();
		
		for (Siviso sivisoEnum : sivisoEnums)
		{
			String siviso = sivisoEnum.toString();
			stringSiviso.put(siviso, sivisoEnum);
		}
		
		return stringSiviso;
	}
	
	private static ArrayList<String> createArrayListOfSivisos()
	{
		Siviso[] sivisoEnums = Siviso.values();
		ArrayList<String> listOfSiviso = new ArrayList<>();
		
		for (Siviso sivisoEnum : sivisoEnums)
		{
			String siviso = sivisoEnum.toString();
			listOfSiviso.add(siviso);
		}
		
		return listOfSiviso;
	}
	
	Siviso(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public static ArrayList<String> getArrayList()
	{
		return sivisos;
	}
	
	public static int getPositionOf(Siviso siviso)
	{
		return sivisos.indexOf(siviso.toString());
	}
	
	public static Siviso getFromPosition(int sivisoAsPosition)
	{
		String sivisoAsString = sivisos.get(sivisoAsPosition);
		Siviso siviso = stringSiviso.get(sivisoAsString);
		
		return siviso;
	}
}
