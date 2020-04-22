package com.betcher.jordan.sivisomapschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.betcher.jordan.sivisomapschedule.SivisoLocation.SivisoLocation;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public class SQLiteLocation extends SQLiteOpenHelper
{
	static final String DATABASE_NAME = "SQLiteLocation";
	static final String TABLE_NAME    = "TableLocation";
	
	
	public static final String COLUMN_0_ID    = "ID";
	public static final String COLUMN_1_NAME    = "Name";
	public static final String COLUMN_2_LATITUDE = "Latitude";
	public static final String COLUMN_3_LONGITUDE = "Longitude";
	public static final String COLUMN_4_SIVISO  = "SiViSo";
	
	public SQLiteLocation(@Nullable Context context)
	{
		super(context, DATABASE_NAME, null, 2);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		String createTable = "CREATE TABLE" + " "
		                     + TABLE_NAME + " "
		                     + "("
		                     + COLUMN_0_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
		                     + COLUMN_1_NAME + " " + "TEXT" + ", "
		                     + COLUMN_2_LATITUDE + " " + "TEXT" + ", "
		                     + COLUMN_3_LONGITUDE + " " + "TEXT" + ", "
		                     + COLUMN_4_SIVISO + " " + "TEXT"
		                     + ")";
		database.execSQL(createTable);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(database);
	}
	
	
	public boolean addData(String name, String latitude, String longitude, Siviso siviso)
	{
		SQLiteDatabase database      = this.getWritableDatabase();
		ContentValues  contentValues = new ContentValues();
		contentValues.put(COLUMN_1_NAME, name);
		contentValues.put(COLUMN_2_LATITUDE, latitude);
		contentValues.put(COLUMN_3_LONGITUDE, longitude);
		contentValues.put(COLUMN_4_SIVISO, siviso.name);
		
		//if data as inserted incorrectly it will return -1
		long result = database.insert(TABLE_NAME, null, contentValues);
		
		if (result == -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void addData(String name, LatLng latLng, Siviso siviso)
	{
		addData(name, latLng.latitude + "", latLng.longitude + "", siviso);
	}
	
	public void addData(SivisoLocation sivisoLocation)
	{
		addData(sivisoLocation.getName(), sivisoLocation.getLatLng(), sivisoLocation.getSiviso());
	}
	
	public HashMap<SivisoLocation, Integer> getDatabaseAsArrayList()
	{
		HashMap<SivisoLocation, Integer> locationIds = new HashMap<SivisoLocation, Integer>();
		SQLiteDatabase                   database    = this.getWritableDatabase();
		Cursor                           query       = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		while (query.moveToNext())
		{
			Integer id        = query.getInt(0);
			String  name      = query.getString(1);
			String  latitude  = query.getString(2);
			String  longitude = query.getString(3);
			Siviso  siviso    = Siviso.fromString(query.getString(4));
			
			SivisoLocation sivisoLocation = new SivisoLocation(name, latitude, longitude, siviso);
			locationIds.put(sivisoLocation, id);
		}
		
		return locationIds;
	}
	
	public int delete(String latitude, String longitude)
	{
		SQLiteDatabase database    = this.getWritableDatabase();
		int            rowsDeleted = database.delete(
				TABLE_NAME,
				(COLUMN_2_LATITUDE + " = ? AND " + COLUMN_3_LONGITUDE + " = ?"),
				new String[]{latitude, longitude}
		);
		return rowsDeleted;
	}
	
	public int delete(SivisoLocation sivisoLocationCurrent)
	{
		return delete(sivisoLocationCurrent.getLatitude(), sivisoLocationCurrent.getLongitude());
	}
	
	public void update(Integer id, SivisoLocation sivisoLocation)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_1_NAME, sivisoLocation.getName());
		contentValues.put(COLUMN_2_LATITUDE, sivisoLocation.getLatitude());
		contentValues.put(COLUMN_3_LONGITUDE, sivisoLocation.getLongitude());
		contentValues.put(COLUMN_4_SIVISO, sivisoLocation.getSiviso().name);
		
		database.update(TABLE_NAME, contentValues, ( COLUMN_0_ID + "=?"), new String[]{id + ""});
	}
}
