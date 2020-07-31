package com.betcher.jordan.siviso.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Respository_Siviso
{
	private Dao_Siviso sivisoDao;
	private LiveData<List<TableRow_Siviso>> allSivisoData;
	
	public static Respository_Siviso instance;
	
	public static Respository_Siviso getInstance(Context context)
	{
		if(instance == null)
		{
			instance = new Respository_Siviso();
			RoomDatabase_Siviso database = RoomDatabase_Siviso
			.getInstance(context);
			instance.sivisoDao = database.sivisoDao();
			instance.allSivisoData = instance.sivisoDao.getAllSivisoData();
		}
		
		return instance;
	}
	
	public void insert(TableRow_Siviso sivisoData) {
		new InsertNoteAsyncTask(sivisoDao).execute(sivisoData);
	}
	
	public void update(TableRow_Siviso sivisoData) {
		new UpdateNoteAsyncTask(sivisoDao).execute(sivisoData);
	}
	
	public void delete(TableRow_Siviso sivisoData) {
		new DeleteNoteAsyncTask(sivisoDao).execute(sivisoData);
	}
	
	public void deleteAllSivisoData() {
		new DeleteAllNotesAsyncTask(sivisoDao).execute();
	}
	
	public LiveData<List<TableRow_Siviso>> getAllSivisoData() {
		return allSivisoData;
	}
	
	private static class InsertNoteAsyncTask extends AsyncTask<TableRow_Siviso, Void, Void> {
		private Dao_Siviso sivisoDao;
		
		private InsertNoteAsyncTask(Dao_Siviso sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(
		TableRow_Siviso... sivisoDatas) {
			sivisoDao.insert(sivisoDatas[0]);
			return null;
		}
	}
	
	private static class UpdateNoteAsyncTask extends AsyncTask<TableRow_Siviso, Void, Void> {
		private Dao_Siviso sivisoDao;
		
		private UpdateNoteAsyncTask(Dao_Siviso sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(
		TableRow_Siviso... sivisoDatas) {
			sivisoDao.update(sivisoDatas[0]);
			return null;
		}
	}
	
	private static class DeleteNoteAsyncTask extends AsyncTask<TableRow_Siviso, Void, Void> {
		private Dao_Siviso sivisoDao;
		
		private DeleteNoteAsyncTask(Dao_Siviso sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(
		TableRow_Siviso... sivisoDatas) {
			sivisoDao.delete(sivisoDatas[0]);
			return null;
		}
	}
	
	private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private Dao_Siviso sivisoDao;
		
		private DeleteAllNotesAsyncTask(Dao_Siviso sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(Void... voids) {
			sivisoDao.deleteAllSivisoData();
			return null;
		}
	}
}
