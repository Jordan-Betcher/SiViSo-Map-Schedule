package com.betcher.jordan.siviso.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SivisoData.class}, version = 3)
public abstract class SivisoDatabase extends RoomDatabase
{
	private static final String TAG = "SivisoDatabase";
	
	private static SivisoDatabase instance;
	
	public abstract SivisoDao sivisoDao();
	
	public static synchronized SivisoDatabase getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(),
			                                SivisoDatabase.class, "SivisoDatabase")
			               .fallbackToDestructiveMigration()
			               .build();
		}
		return instance;
	}
}
