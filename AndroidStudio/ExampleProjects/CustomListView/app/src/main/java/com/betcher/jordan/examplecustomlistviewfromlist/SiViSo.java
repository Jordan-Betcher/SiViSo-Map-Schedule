package com.betcher.jordan.examplecustomlistviewfromlist;

import java.util.ArrayList;
import java.util.Arrays;

//https://www.baeldung.com/java-enum-values
public enum SiViSo
{
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound");
	
	public final String name;
	private static ArrayList<String> valuesAsStrings = createValuesAsStrings();
	
	private SiViSo(String name) {
		this.name = name;
	}
	
	private static ArrayList<String> createValuesAsStrings()
	{
		SiViSo[] sivisos = SiViSo.values();
		ArrayList<String> sivisosAsString = new ArrayList<String>(sivisos.length);
		
		for(int index = 0; index < sivisos.length; index++)
		{
			sivisosAsString.add(index, sivisos[index].name);
		}
		
		return  sivisosAsString;
	}
	
	public static int indexOf(String name)
	{
		return valuesAsStrings.indexOf(name);
	}
	
	public static int indexOf(SiViSo siViSo)
	{
		return indexOf(siViSo.name);
	}
	
	public static ArrayList<String> getValuesAsStrings()
	{
		return valuesAsStrings;
	}
}


