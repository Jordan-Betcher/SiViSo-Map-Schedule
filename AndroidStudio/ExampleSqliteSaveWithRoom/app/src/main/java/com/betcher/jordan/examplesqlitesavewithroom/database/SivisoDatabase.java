package com.betcher.jordan.examplesqlitesavewithroom.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {SivisoData.class}, version = 1)
public abstract class SivisoDatabase extends RoomDatabase
{
	private static SivisoDatabase instance;
	
	public abstract SivisoDao sivisoDao();
	
	public static synchronized SivisoDatabase getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(),
			                                SivisoDatabase.class, "SivisoDatabase")
			               .fallbackToDestructiveMigration()
			               .addCallback(roomCallback)
			               .build();
		}
		return instance;
	}
	
	private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
			new PopulateDbAsyncTask(instance).execute();
		}
	};
	
	private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private SivisoDao sivisoDao;
		
		private PopulateDbAsyncTask(SivisoDatabase sivisoDatabase) {
			sivisoDao = sivisoDatabase.sivisoDao();
		}
		
		@Override
		protected Void doInBackground(Void... voids) {
			sivisoDao.insert(new SivisoData("Default", "None", 0, 0));
			return null;
		}
	}
}
