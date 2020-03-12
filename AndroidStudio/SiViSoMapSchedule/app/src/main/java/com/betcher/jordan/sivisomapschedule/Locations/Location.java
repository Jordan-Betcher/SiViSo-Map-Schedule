package com.betcher.jordan.sivisomapschedule.Locations;

import com.betcher.jordan.sivisomapschedule.SiViSo;

public class Location
{
	private String name;
	private String address;
	private SiViSo siviso;
	
	public Location(String name, String address, SiViSo siviso)
	{
		this.name = name;
		this.address = address;
		this.siviso = siviso;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public SiViSo getSiviso()
	{
		return siviso;
	}
}
