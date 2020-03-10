package com.betcher.jordan.sivisomapschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteLocation extends SQLiteOpenHelper
{
	static final String DATABASE_NAME = "SQLiteLocation";
	static final String TABLE_NAME    = "TableLocation";
	
	
	public static final String COLUMN_0_ID    = "ID";
	public static final String COLUMN_1_NAME    = "Name";
	public static final String COLUMN_2_ADDRESS = "Address";
	public static final String COLUMN_3_SIVISO  = "SiViSo";
	
	public SQLiteLocation(@Nullable Context context)
	{
		super(context, DATABASE_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		String createTable = "CREATE TABLE" + " "
		                     + TABLE_NAME + " "
		                     + "("
		                     + COLUMN_0_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
		                     + COLUMN_1_NAME + " " + "TEXT" + ", "
		                     + COLUMN_2_ADDRESS + " " + "TEXT" + ", "
		                     + COLUMN_3_SIVISO + " " + "TEXT"
		                     + ")";
		database.execSQL(createTable);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(database);
	}
	
	
	public boolean addData(String name, String address, SiViSo siviso)
	{
		SQLiteDatabase database      = this.getWritableDatabase();
		ContentValues  contentValues = new ContentValues();
		contentValues.put(COLUMN_1_NAME, name);
		contentValues.put(COLUMN_2_ADDRESS, address);
		contentValues.put(COLUMN_3_SIVISO, siviso.name);
		
		//if data as inserted incorrectly it will return -1
		long result = database.insert(TABLE_NAME, null, contentValues);
		
		if (result == -1)
		{
			return false;
		} else
		{
			return true;
		}
	}
	
	public HashMap<Location, Integer> getDatabaseAsArrayList()
	{
		HashMap<Location, Integer> locationIds = new HashMap<Location, Integer>();
		SQLiteDatabase      database  = this.getWritableDatabase();
		Cursor              query     = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		while (query.moveToNext())
		{
			Integer id      = query.getInt(0);
			String name    = query.getString(1);
			String address = query.getString(2);
			SiViSo siviso  = SiViSo.fromString(query.getString(3));
			
			Location location = new Location(name, address, siviso);
			locationIds.put(location, id);
		}
		
		return locationIds;
	}
	
	public int delete(String address)
	{
		SQLiteDatabase database    = this.getWritableDatabase();
		int            rowsDeleted = database.delete(
				TABLE_NAME,
				(COLUMN_2_ADDRESS + "=?"),
				new String[]{address}
		);
		return rowsDeleted;
	}
	
	public int delete(Location locationCurrent)
	{
		return delete(locationCurrent.getAddress());
	}
	
	
	public void update(Integer id, Location location)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_1_NAME, location.getName());
		contentValues.put(COLUMN_2_ADDRESS, location.getAddress());
		contentValues.put(COLUMN_3_SIVISO, location.getSiviso().name);
		
		database.update(TABLE_NAME, contentValues, ( COLUMN_0_ID + "=?"), new String[]{id + ""});
	}
	
}
