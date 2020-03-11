package com.betcher.jordan.sivisomapschedule;

import java.util.ArrayList;

//https://www.baeldung.com/java-enum-values
public enum SiViSo
{
	NONE("None"),
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound");
	
	public final   String            name;
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
	
	public static boolean isSiViSo(String pendingSiViSo)
	{
		if(SiViSo.NONE.name.equals(pendingSiViSo))
		{
			return true;
		}
		else if(SiViSo.SILENT.name.equals(pendingSiViSo))
		{
			return true;
		}
		else if(SiViSo.VIBRATE.name.equals(pendingSiViSo))
		{
			return true;
		}
		else if(SiViSo.SOUND.name.equals(pendingSiViSo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static SiViSo fromString(String pendingSiViSo)
	{
		if(SiViSo.NONE.name.equals(pendingSiViSo))
		{
			return SiViSo.NONE;
		}
		else if(SiViSo.SILENT.name.equals(pendingSiViSo))
		{
			return SiViSo.SILENT;
		}
		else if(SiViSo.VIBRATE.name.equals(pendingSiViSo))
		{
			return SiViSo.VIBRATE;
		}
		else if(SiViSo.SOUND.name.equals(pendingSiViSo))
		{
			return SiViSo.SOUND;
		}
		else
		{
			return null;
		}
	}
}


