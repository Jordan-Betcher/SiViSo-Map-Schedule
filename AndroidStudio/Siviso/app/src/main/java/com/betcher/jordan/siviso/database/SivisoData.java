package com.betcher.jordan.siviso.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Siviso")
public class SivisoData
{
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String name;
	private String siviso;
	private double latitude;
	private double longitude;
	private double radius;
	
	public SivisoData(String name, String siviso, double latitude, double longitude, double radius)
	{
		this.name = name;
		this.siviso = siviso;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSiviso()
	{
		return siviso;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public double getRadius() { return radius; }
}
