package com.betcher.jordan.examplesqlitesavewithroom.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SivisoRepository
{
	private SivisoDao sivisoDao;
	private LiveData<List<SivisoData>> allSivisoData;
	
	public SivisoRepository(Application application) {
		SivisoDatabase database = SivisoDatabase.getInstance(application);
		sivisoDao = database.sivisoDao();
		allSivisoData = sivisoDao.getAllSivisoData();
	}
	
	public void insert(SivisoData sivisoData) {
		new InsertNoteAsyncTask(sivisoDao).execute(sivisoData);
	}
	
	public void update(SivisoData sivisoData) {
		new UpdateNoteAsyncTask(sivisoDao).execute(sivisoData);
	}
	
	public void delete(SivisoData sivisoData) {
		new DeleteNoteAsyncTask(sivisoDao).execute(sivisoData);
	}
	
	public void deleteAllSivisoData() {
		new DeleteAllNotesAsyncTask(sivisoDao).execute();
	}
	
	public LiveData<List<SivisoData>> getAllSivisoData() {
		return allSivisoData;
	}
	
	private static class InsertNoteAsyncTask extends AsyncTask<SivisoData, Void, Void> {
		private SivisoDao sivisoDao;
		
		private InsertNoteAsyncTask(SivisoDao sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(SivisoData... sivisoDatas) {
			sivisoDao.insert(sivisoDatas[0]);
			return null;
		}
	}
	
	private static class UpdateNoteAsyncTask extends AsyncTask<SivisoData, Void, Void> {
		private SivisoDao sivisoDao;
		
		private UpdateNoteAsyncTask(SivisoDao sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(SivisoData... sivisoDatas) {
			sivisoDao.update(sivisoDatas[0]);
			return null;
		}
	}
	
	private static class DeleteNoteAsyncTask extends AsyncTask<SivisoData, Void, Void> {
		private SivisoDao sivisoDao;
		
		private DeleteNoteAsyncTask(SivisoDao sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(SivisoData... sivisoDatas) {
			sivisoDao.delete(sivisoDatas[0]);
			return null;
		}
	}
	
	private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private SivisoDao sivisoDao;
		
		private DeleteAllNotesAsyncTask(SivisoDao sivisoDao) {
			this.sivisoDao = sivisoDao;
		}
		
		@Override
		protected Void doInBackground(Void... voids) {
			sivisoDao.deleteAllSivisoData();
			return null;
		}
	}
}
