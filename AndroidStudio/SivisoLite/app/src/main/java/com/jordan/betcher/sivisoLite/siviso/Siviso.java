package com.jordan.betcher.sivisoLite.siviso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public enum Siviso
{
	NONE("None"),
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound"),
	MAX("Max");
	
	private String name;
	
	Siviso(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	private static ArrayList<String> sivisos = createArrayListOfSivisos();
	
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
	
	public static int getPositionOf(Siviso siviso)
	{
		return sivisos.indexOf(siviso.toString());
	}
	
	public static ArrayList<String> getArrayList()
	{
		return sivisos;
	}
}
