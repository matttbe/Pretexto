package com.needsreal.social.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class HistoryOpenHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 0;
	private static final String DATABASE_NAME = "History";

	// Search table
	private static final String SEARCH_TABLE_NAME = "search";
	private static final String SEARCH_COLUMN_SEARCH = "search";
	private static final String SEARCH_COLUMN_LAT = "lat";
	private static final String SEARCH_COLUMN_LNG = "lng";
	private static final String SEARCH_COLUMN_TIMESTAMP = "timestamp";
	private static final String SEARCH_TABLE_CREATE =
			"CREATE TABLE " + SEARCH_TABLE_NAME + " ("
			+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ SEARCH_COLUMN_SEARCH + " TEXT NOT NULL, "
			+ SEARCH_COLUMN_LAT + " REAL, "
			+ SEARCH_COLUMN_LNG + " REAL, "
			+ SEARCH_COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL);";

	public HistoryOpenHelper (Context context)
	{
		super (context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate (SQLiteDatabase db)
	{
		db.execSQL (SEARCH_TABLE_CREATE);
	}

	@Override
	public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}
