package com.betcher.jordan.examplesqlitedatabasecode;

//https://www.baeldung.com/java-enum-values
public enum SiViSo {
	SILENT("Silent"),
	VIBRATE("Vibrate"),
	SOUND("Sound");
	
	public final String name;
	
	private SiViSo(String name) {
		this.name = name;
	}
}


