package edu.cmu.malicioustaskreminder.db;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.malicioustaskreminder.ViewTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
	public List<ViewTask> getAllTasks() 
	{
		List<ViewTask> listOfTasks = new ArrayList<ViewTask>();
		
		Cursor cursor = database.query("TaskData", new String[] {"_id", "description", "fromTime", "toTime", "date"}, 
				null, null, null, null, "_id");
		
		 cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    ViewTask taskDetail = cursorToTaskDetail(cursor);
		      listOfTasks.add(taskDetail);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return listOfTasks;
	} // end method getAllGroup
	
	private ViewTask cursorToTaskDetail(Cursor cursor) {
		ViewTask taskDetail = new ViewTask();
		taskDetail.setDescriptionString(cursor.getString(1));
		taskDetail.setToTimeString(cursor.getString(2));
		taskDetail.setFromTimeString(cursor.getString(3));
		taskDetail.setDateString(cursor.getString(4));
		return taskDetail;
	}

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
}
