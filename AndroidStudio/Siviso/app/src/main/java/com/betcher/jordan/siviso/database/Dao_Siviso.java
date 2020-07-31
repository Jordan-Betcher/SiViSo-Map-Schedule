package com.betcher.jordan.siviso.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Dao_Siviso
{
	@Insert
	void insert(DatabaseFormatted_Siviso sivisoData);
	
	@Update
	void update(DatabaseFormatted_Siviso sivisoData);
	
	@Delete
	void delete(DatabaseFormatted_Siviso sivisoData);
	
	@Query("DELETE FROM Siviso")
	void deleteAllSivisoData();
	
	@Query("SELECT * FROM Siviso ORDER BY name DESC")
	LiveData<List<DatabaseFormatted_Siviso>> getAllSivisoData();
}
