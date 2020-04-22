package com.betcher.jordan.sivisomapschedule;

import java.util.ArrayList;

//https://www.baeldung.com/java-enum-values
public enum Siviso
{
	NONE("None"),
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound");
	
	public final   String            name;
	private static ArrayList<String> valuesAsStrings = createValuesAsStrings();
	
	private Siviso(String name) {
		this.name = name;
	}
	
	private static ArrayList<String> createValuesAsStrings()
	{
		Siviso[]          sivisos         = Siviso.values();
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
	
	public static int indexOf(Siviso siViSo)
	{
		return indexOf(siViSo.name);
	}
	
	public static ArrayList<String> getValuesAsStrings()
	{
		return valuesAsStrings;
	}
	
	public static boolean isSiViSo(String pendingSiViSo)
	{
		if(Siviso.NONE.name.equals(pendingSiViSo))
		{
			return true;
		}
		else if(Siviso.SILENT.name.equals(pendingSiViSo))
		{
			return true;
		}
		else if(Siviso.VIBRATE.name.equals(pendingSiViSo))
		{
			return true;
		}
		else if(Siviso.SOUND.name.equals(pendingSiViSo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Siviso fromString(String pendingSiViSo)
	{
		if(Siviso.NONE.name.equals(pendingSiViSo))
		{
			return Siviso.NONE;
		}
		else if(Siviso.SILENT.name.equals(pendingSiViSo))
		{
			return Siviso.SILENT;
		}
		else if(Siviso.VIBRATE.name.equals(pendingSiViSo))
		{
			return Siviso.VIBRATE;
		}
		else if(Siviso.SOUND.name.equals(pendingSiViSo))
		{
			return Siviso.SOUND;
		}
		else
		{
			return null;
		}
	}
}


