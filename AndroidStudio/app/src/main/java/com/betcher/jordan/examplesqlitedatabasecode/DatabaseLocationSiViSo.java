package com.betcher.jordan.examplesqlitedatabasecode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseLocationSiViSo extends SQLiteOpenHelper
{
	static final String DATABASE_NAME = "DatabaseLocationSiViSo";
	static final String TABLE_NAME = "TableLocationSiViSo";
	
	public static final String COLUMN_1_NAME = "Location";
	public static final String COLUMN_2_NAME = "SiViSo";
	
	public DatabaseLocationSiViSo(@Nullable Context context)
	{
		super(context, DATABASE_NAME, null, 2);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String createTable = "CREATE TABLE" + " "
		                     + TABLE_NAME + " "
		                     + "(ID INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
		                     + COLUMN_1_NAME + " " + "TEXT" + ", "
		                     + COLUMN_2_NAME + " " + "TEXT"
		                     + ")";
		db.execSQL(createTable);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	
	public boolean addData(String location, SiViSo siviso) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_1_NAME, location);
		contentValues.put(COLUMN_2_NAME, siviso.name);
		
		long result = db.insert(TABLE_NAME, null, contentValues);
		
		//if data as inserted incorrectly it will return -1
		if (result == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	public String getDatabaseAsString()
	{
		ArrayList<String> theList = new ArrayList<>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		while (data.moveToNext())
		{
			String row = "";
			
			for(int column = 0; column < data.getColumnCount(); column++)
			{
				row += data.getString(column);
				row += " ";
			}
			theList.add(row);
			
		}
		
		String display = "";
		for(String line : theList)
		{
			display += line;
			display += "\n";
		}
		return display;
	}
}
