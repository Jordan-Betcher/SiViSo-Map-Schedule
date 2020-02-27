package com.betcher.jordan.examplesqlitedatabasecode;

//https://www.baeldung.com/java-enum-values
public enum SiViSo
{
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound");
	
	public final String name;
	
	private SiViSo(String name) {
		this.name = name;
	}
	
	public static boolean isSiViSo(String pendingSiViSo)
	{
		if(SiViSo.SILENT.name.equals(pendingSiViSo))
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
		if(SiViSo.SILENT.name.equals(pendingSiViSo))
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


