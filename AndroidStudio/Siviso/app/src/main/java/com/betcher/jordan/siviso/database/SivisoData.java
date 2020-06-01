package com.betcher.jordan.siviso.database;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.betcher.jordan.siviso.Defaults;
import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = Defaults.DATABASE_NAME)
public class SivisoData
{
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String name;
	private String siviso;
	private double latitude;
	private double longitude;
	
	public SivisoData(String name, String siviso, double latitude, double longitude)
	{
		this.name = name;
		this.siviso = siviso;
		this.latitude = latitude;
		this.longitude = longitude;
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
	
	@Ignore
	public LatLng getLatLng(){return new LatLng(latitude, longitude);}
	
	@Ignore
	@Override
	public boolean equals(@Nullable Object obj)
	{
		if(obj instanceof SivisoData)
		{
			SivisoData other = (SivisoData) obj;
			
			if(other.getName() == getName()
			&& other.getSiviso() == getSiviso()
			&& other.getId() == getId()
			&& other.getLatitude() == getLatitude()
			&& other.getLongitude() == getLongitude())
			{
				return true;
			}
		}
		
		return false;
	}
}
