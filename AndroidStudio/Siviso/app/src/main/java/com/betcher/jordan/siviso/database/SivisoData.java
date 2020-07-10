package com.betcher.jordan.siviso.database;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.siviso.Siviso;
import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = Defaults.DATABASE_NAME)
public class SivisoData
{
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String name;
	private String sivisoName;
	private double latitude;
	private double longitude;
	
	public SivisoData(String name, String sivisoName, double latitude, double longitude)
	{
		this.name = name;
		this.sivisoName = sivisoName;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int id()
	{
		return id;
	}
	
	public String name()
	{
		return name;
	}
	
	String sivisoName()
	{
		return sivisoName;
	}
	
	public double latitude()
	{
		return latitude;
	}
	
	public double longitude()
	{
		return longitude;
	}
	
	@Ignore
	public LatLng latLng(){return new LatLng(latitude, longitude);}
	@Ignore
	public Siviso siviso(){return Siviso.siviso(sivisoName);}
	
	@Ignore
	@Override
	public boolean equals(@Nullable Object obj)
	{
		if(obj instanceof SivisoData)
		{
			SivisoData other = (SivisoData) obj;
			
			if(other.name() == name()
			&& other.sivisoName() == sivisoName()
			&& other.id() == id()
			&& other.latitude() == latitude()
			&& other.longitude() == longitude())
			{
				return true;
			}
		}
		
		return false;
	}
}
