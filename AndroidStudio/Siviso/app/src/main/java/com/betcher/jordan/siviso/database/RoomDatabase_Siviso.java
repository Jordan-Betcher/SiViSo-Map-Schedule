package com.betcher.jordan.siviso.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TableRow_Siviso.class}, version = 4)
public abstract class RoomDatabase_Siviso extends RoomDatabase
{
	private static final String TAG = "RoomDatabase_Siviso";
	
	private static RoomDatabase_Siviso instance;
	
	public abstract Dao_Siviso sivisoDao();
	
	public static synchronized RoomDatabase_Siviso getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(),
			                                RoomDatabase_Siviso.class, "RoomDatabase_Siviso")
			               .fallbackToDestructiveMigration()
			               .build();
		}
		return instance;
	}
}
