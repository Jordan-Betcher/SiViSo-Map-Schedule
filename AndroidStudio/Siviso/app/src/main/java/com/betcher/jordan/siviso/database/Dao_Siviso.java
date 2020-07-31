package com.betcher.jordan.siviso.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface Dao_Siviso
{
	@Insert
	void insert(TableRow_Siviso sivisoData);
	
	@Update
	void update(TableRow_Siviso sivisoData);
	
	@Delete
	void delete(TableRow_Siviso sivisoData);
	
	@Query("DELETE FROM " + TableRow_Siviso.TABLE_NAME)
	void deleteAllSivisoData();
	
	@Query("SELECT * FROM " + TableRow_Siviso.TABLE_NAME + " ORDER BY name DESC")
	LiveData<List<TableRow_Siviso>> getAllSivisoData();
}
