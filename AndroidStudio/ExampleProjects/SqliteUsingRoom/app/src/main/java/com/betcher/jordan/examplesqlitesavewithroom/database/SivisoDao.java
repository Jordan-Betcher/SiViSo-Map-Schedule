package com.betcher.jordan.examplesqlitesavewithroom.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SivisoDao
{
	@Insert
	void insert(SivisoData sivisoData);
	
	@Update
	void update(SivisoData sivisoData);
	
	@Delete
	void delete(SivisoData sivisoData);
	
	@Query("DELETE FROM Siviso")
	void deleteAllSivisoData();
	
	@Query("SELECT * FROM Siviso ORDER BY name DESC")
	LiveData<List<SivisoData>> getAllSivisoData();
}
