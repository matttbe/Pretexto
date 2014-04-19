package com.needsreal.social.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class MessagesOpenHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 0;
	private static final String DATABASE_NAME = "Messages";

	// Messages
	private static final String MSG_TABLE_NAME = "Messages";
	private static final String MSG_COLUMN_FROM = "from";
	private static final String MSG_COLUMN_TO = "to";
	private static final String MSG_COLUMN_CONTENT = "content";
	private static final String MSG_COLUMN_TIMESTAMP = "timestamp";
	private static final String MSG_TABLE_CREATE =
			"CREATE TABLE " + MSG_TABLE_NAME + " ("
			+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ MSG_COLUMN_FROM + " TEXT NOT NULL, "
			+ MSG_COLUMN_TO + " TEXT NOT NULL, "
			+ MSG_COLUMN_CONTENT + " TEXT NOT NULL, "
			+ MSG_COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL);";

	public MessagesOpenHelper (Context context)
	{
		super (context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate (SQLiteDatabase db)
	{
		db.execSQL (MSG_TABLE_CREATE);
	}

	@Override
	public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}
