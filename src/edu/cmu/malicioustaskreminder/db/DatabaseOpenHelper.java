package edu.cmu.malicioustaskreminder.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	// public constructor
	public DatabaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) 
	{
		super(context, name, factory, version);
	} // end DatabaseOpenHelper constructor

	// creates the Groups table when the database is created
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// query to create a new table named users
		String createQuery = "CREATE TABLE TaskData" +
				"(_id integer primary key autoincrement," +
				"description TEXT, toTime TEXT, fromTime TEXT, date TEXT);";

		db.execSQL(createQuery); // execute the query
	} // end method onCreate

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, 
			int newVersion) 
	{
	} // end method onUpgrade

}
