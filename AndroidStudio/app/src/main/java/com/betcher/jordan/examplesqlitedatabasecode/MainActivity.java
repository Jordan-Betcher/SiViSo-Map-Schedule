package com.betcher.jordan.examplesqlitedatabasecode;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
	
	TextView textViewDatabase;
	DatabaseLocationSiViSo databaseLocationSiViSo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textViewDatabase = (TextView) findViewById(R.id.textViewDatabase);
		databaseLocationSiViSo = new DatabaseLocationSiViSo(this);
		
		displayDatabase();
	}
	
	public void addToDatabase(View view)
	{
		boolean work = databaseLocationSiViSo.addData("test", SiViSo.SILENT);
		
		if(work)
		{
			Toast.makeText(this, "It work", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "It no work", Toast.LENGTH_LONG).show();
		}
		displayDatabase();
	}
	
	public void deleteLastFromDatabase(View view)
	{
	
	}
	
	public void appendTestToLastOfDatabase(View view)
	{
		
	
		
	}
	
	private void displayDatabase()
	{
		
		String display = databaseLocationSiViSo.getDatabaseAsString();
		textViewDatabase.setText(display);
	}
}
