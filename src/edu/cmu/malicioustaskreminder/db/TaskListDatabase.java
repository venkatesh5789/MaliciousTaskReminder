package edu.cmu.malicioustaskreminder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class TaskListDatabase {
	// database name
	private static final String DATABASE_NAME = "TaskData";
	private SQLiteDatabase database; // database object
	private DatabaseOpenHelper databaseOpenHelper; // database helper


	// public constructor for DatabaseConnector
	public TaskListDatabase(Context context) 
	{
		// create a new DatabaseOpenHelper
		databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
	} // end DatabaseConnector constructor

	// open the database connection
	public void open() throws SQLException 
	{
		// create or open a database for reading/writing
		database = databaseOpenHelper.getWritableDatabase();
	} // end method open

	// close the database connection
	public void close() 
	{
		if (database != null)
			database.close(); // close the database connection
	} // end method close

	// new Group in the database
	public long insertTask(String description, String fromTime, String toTime, String date) 
	{
		ContentValues newTask = new ContentValues();
		newTask.put("description", description);
		newTask.put("fromTime", fromTime);
		newTask.put("toTime", toTime);
		newTask.put("date", date);

		open(); // open the database
		return database.insert("TaskData", null, newTask);
		// close the database
	} // end method insertGroup

	// inserts a new Group in the database
	public void updateTask(long id, String description, String fromTime, String toTime, String date) 
	{
		ContentValues editTask = new ContentValues();
		editTask.put("description", description);
		editTask.put("fromTime", fromTime);
		editTask.put("toTime", toTime);
		editTask.put("date", date);

		open(); // open the database
		database.update("TaskData", editTask, "_id=" + id, null);
		close(); // close the database
	} // end method updateGroup

	// return a Cursor with all Group information in the database
	public Cursor getAllTasks() 
	{
		return database.query("TaskData", new String[] {"_id", "description", "fromTime", "toTime", "date"}, 
				null, null, null, null, "_id");
	} // end method getAllGroup

	// get a Cursor containing all information about the Group specified
	// by the given id
	public Cursor getOneTask(long id) 
	{
		return database.query(
				"TaskData", null, "_id=" + id, null, null, null, null);
	} // end method getOneGroup
	
	public Cursor getOneTask(String description) 
	{
		return database.query(
				"TaskData", null, "description='" + description + "'", null, null, null, null);
	} // end method getOneGroup

	// delete the Group specified by the given String name
	public void deleteTask(long id) 
	{
		open(); // open the database
		database.delete("TaskData", "_id=" + id, null);
		close(); // close the database
	} // end method deleteGroup

	private class DatabaseOpenHelper extends SQLiteOpenHelper 
	{
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
					"toTime TEXT, fromTime TEXT, date TEXT);";

			db.execSQL(createQuery); // execute the query
		} // end method onCreate

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, 
				int newVersion) 
		{
		} // end method onUpgrade
	} // end class DatabaseOpenHelper
}
