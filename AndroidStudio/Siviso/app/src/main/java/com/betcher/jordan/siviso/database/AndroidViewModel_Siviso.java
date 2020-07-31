package com.betcher.jordan.siviso.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AndroidViewModel_Siviso extends AndroidViewModel
{
	private Respository_Siviso repository;
	private LiveData<List<TableRow_Siviso>> allSivisoData;
	
	public AndroidViewModel_Siviso(@NonNull Application application) {
		super(application);
		repository = Respository_Siviso.getInstance(application);
		allSivisoData = repository.getAllSivisoData();
	}
	
	public void insert(TableRow_Siviso sivisoData) {
		repository.insert(sivisoData);
	}
	
	public void update(TableRow_Siviso sivisoData) {
		repository.update(sivisoData);
	}
	
	public void delete(TableRow_Siviso sivisoData) {
		repository.delete(sivisoData);
	}
	
	public void deleteAllSivisoData() {
		repository.deleteAllSivisoData();
	}
	
	public LiveData<List<TableRow_Siviso>> getAllSivisoData() {
		return allSivisoData;
	}
}
