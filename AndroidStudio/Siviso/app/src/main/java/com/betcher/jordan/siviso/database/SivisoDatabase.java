package com.betcher.jordan.siviso.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SivisoData.class}, version = 2)
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
