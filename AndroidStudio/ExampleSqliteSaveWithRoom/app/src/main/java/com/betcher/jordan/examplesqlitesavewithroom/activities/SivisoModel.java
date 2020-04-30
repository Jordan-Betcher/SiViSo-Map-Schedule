package com.betcher.jordan.examplesqlitesavewithroom.activities;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.betcher.jordan.examplesqlitesavewithroom.database.SivisoData;
import com.betcher.jordan.examplesqlitesavewithroom.database.SivisoRepository;

import java.util.List;

public class SivisoModel extends AndroidViewModel
{
	private SivisoRepository repository;
	private LiveData<List<SivisoData>> allSivisoData;
	
	public SivisoModel(@NonNull Application application) {
		super(application);
		repository = new SivisoRepository(application);
		allSivisoData = repository.getAllSivisoData();
	}
	
	public void insert(SivisoData sivisoData) {
		repository.insert(sivisoData);
	}
	
	public void update(SivisoData sivisoData) {
		repository.update(sivisoData);
	}
	
	public void delete(SivisoData sivisoData) {
		repository.delete(sivisoData);
	}
	
	public void deleteAllSivisoData() {
		repository.deleteAllSivisoData();
	}
	
	public LiveData<List<SivisoData>> getAllSivisoData() {
		return allSivisoData;
	}
}
