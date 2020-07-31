package com.betcher.jordan.siviso.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AndroidViewModel_Siviso extends AndroidViewModel
{
	private Respository_Siviso repository;
	private LiveData<List<DatabaseFormatted_Siviso>> allSivisoData;
	
	public AndroidViewModel_Siviso(@NonNull Application application) {
		super(application);
		repository = Respository_Siviso.getInstance(application);
		allSivisoData = repository.getAllSivisoData();
	}
	
	public void insert(DatabaseFormatted_Siviso sivisoData) {
		repository.insert(sivisoData);
	}
	
	public void update(DatabaseFormatted_Siviso sivisoData) {
		repository.update(sivisoData);
	}
	
	public void delete(DatabaseFormatted_Siviso sivisoData) {
		repository.delete(sivisoData);
	}
	
	public void deleteAllSivisoData() {
		repository.deleteAllSivisoData();
	}
	
	public LiveData<List<DatabaseFormatted_Siviso>> getAllSivisoData() {
		return allSivisoData;
	}
}
