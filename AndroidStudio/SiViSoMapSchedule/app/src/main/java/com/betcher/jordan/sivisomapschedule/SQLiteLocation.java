package com.betcher.jordan.sivisomapschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteLocation extends SQLiteOpenHelper
{
	static final String DATABASE_NAME = "DatabaseLocationSiViSo";
	static final String TABLE_NAME = "TableLocationSiViSo";
	
	public static final String COLUMN_1_NAME    = "Name";
	public static final String COLUMN_2_ADDRESS = "Address";
	public static final String COLUMN_3_SIVISO  = "SiViSo";
	
	public SQLiteLocation(@Nullable Context context)
	{
		super(context, DATABASE_NAME, null, 2);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		String createTable = "CREATE TABLE" + " "
		                     + TABLE_NAME + " "
		                     + "(ID INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
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
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_1_NAME, name);
		contentValues.put(COLUMN_2_ADDRESS, address);
		contentValues.put(COLUMN_3_SIVISO, siviso.name);
		
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
	
	public ArrayList<ArrayList<String>> getDatabaseAsArrayList()
	{
		ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor query = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		while (query.moveToNext())
		{
			ArrayList<String> row = new ArrayList<>();
			
			for (int column = 0; column < query.getColumnCount(); column++)
			{
				String data = query.getString(column);
				row.add(data);
			}
			
			arrayLists.add(row);
		}
		
		return arrayLists;
	}
	
	public int delete(String name)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		int rowsDeleted = database.delete(TABLE_NAME, (COLUMN_1_NAME + "=?"), new String[]{name});
		return rowsDeleted;
	}
	
	public void update(String name, String address, SiViSo siviso)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_2_ADDRESS, address);
		contentValues.put(COLUMN_3_SIVISO, siviso.name);
		
		database.update(TABLE_NAME, contentValues, (COLUMN_1_NAME + "=?"), new String[]{name});
	}
	
	public void update(String name, SiViSo siviso)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_3_SIVISO, siviso.name);
		
		database.update(TABLE_NAME, contentValues, (COLUMN_1_NAME + "=?"), new String[]{name});
	}
	
	public void update(String name, String address)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_2_ADDRESS, address);
		
		database.update(TABLE_NAME, contentValues, (COLUMN_1_NAME + "=?"), new String[]{name});
	}
	
}
