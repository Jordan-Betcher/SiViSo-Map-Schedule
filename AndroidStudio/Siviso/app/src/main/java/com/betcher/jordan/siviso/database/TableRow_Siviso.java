package com.betcher.jordan.siviso.database;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.betcher.jordan.siviso.siviso.SivisoRingmode;
import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = TableRow_Siviso.TABLE_NAME)
public class TableRow_Siviso
{
	@Ignore
	public static final String TABLE_NAME = "SivisoTable";
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String name;
	private String sivisoName;
	private double latitude;
	private double longitude;
	
	public TableRow_Siviso(String name, String sivisoName, double latitude, double longitude)
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
	public SivisoRingmode siviso(){return SivisoRingmode
	.siviso(sivisoName);}
	
	@Ignore
	@Override
	public boolean equals(@Nullable Object obj)
	{
		if(obj instanceof TableRow_Siviso)
		{
			TableRow_Siviso other = (TableRow_Siviso) obj;
			
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
