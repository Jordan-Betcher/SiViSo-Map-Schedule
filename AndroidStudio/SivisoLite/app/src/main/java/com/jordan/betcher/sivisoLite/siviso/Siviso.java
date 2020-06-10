package com.jordan.betcher.sivisoLite.siviso;

import java.util.ArrayList;

public class Siviso
{
	static ArrayList<String> sivisos = createSivisos();
	
	private static ArrayList<String> createSivisos()
	{
		ArrayList<String> tempSivisos = new ArrayList<>();
		tempSivisos.add("None");
		tempSivisos.add("Silent");
		tempSivisos.add("Vibrate");
		tempSivisos.add("Sound");
		tempSivisos.add("Max");
		
		return tempSivisos;
	}
	
	public static ArrayList<String> getArray()
	{
		return sivisos;
	}
}
