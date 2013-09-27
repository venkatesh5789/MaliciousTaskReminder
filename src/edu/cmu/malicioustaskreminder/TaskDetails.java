package edu.cmu.malicioustaskreminder;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import edu.cmu.malicioustaskreminder.db.*;

public class TaskDetails extends Activity implements OnTimeSelectedListener, OnDateSelectedListener {

	private TextView dateDisplay;
	private TextView fromTimeDisplay;
	private TextView toTimeDisplay;
	private DialogFragment taskDateFragment = new DatePickerFragment();
	private DialogFragment timeFragment = new TimePickerFragment();
	
	private int year;
	private int month;
	private int day;
	
	private int fromHour;
	private int fromMinute;
	private int toHour;
	private int toMinute;
	
	private String description;
	
	private static String typeOfTag = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_details);
		initializeFromTime();
		initializeToTime();
		initializeCurrentDate();
	}

	private void initializeCurrentDate() {
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		dateDisplay = (TextView) findViewById(R.id.taskDate);
		dateDisplay.setText(new StringBuilder()
		// Month is 0 based, just add 1
		.append(month + 1).append("-").append(day).append("-")
		.append(year).append(" "));
		
	}

	private void initializeToTime() {
		final Calendar c = Calendar.getInstance();
		toHour = c.get(Calendar.HOUR_OF_DAY);
		toMinute = c.get(Calendar.MINUTE);	
		toTimeDisplay = (TextView)findViewById(R.id.toTime);
		toTimeDisplay.setText(new StringBuilder().append(toHour).append(":").append(toMinute));	
	}

	private void initializeFromTime() {
		final Calendar c = Calendar.getInstance();
		fromHour = c.get(Calendar.HOUR_OF_DAY);
		fromMinute = c.get(Calendar.MINUTE);	
		fromTimeDisplay = (TextView)findViewById(R.id.fromTime);
		fromTimeDisplay.setText(new StringBuilder().append(fromHour).append(":").append(fromMinute));	
	}
	
	public void inputTaskDate(View v) {
		taskDateFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void inputFromTime(View v) {
		typeOfTag = "from";
		timeFragment.show(getFragmentManager(), "fromTimePicker");
	}
	
	public void inputToTime(View v) {
		typeOfTag = "to";
		timeFragment.show(getFragmentManager(), "toTimePicker");
	}
	
	public void onTimeSet(int hour, int minute) {
		StringBuilder timeString = new StringBuilder();
		
		if(typeOfTag.equalsIgnoreCase("to")) {
			toHour = hour;
			toMinute = minute;
			toTimeDisplay = (TextView)findViewById(R.id.toTime);
			timeString.append((hour<10)? "0"+hour : hour).append(":");
			timeString.append((minute<10)? "0"+minute : minute);
			toTimeDisplay.setText(timeString);	
		} else if(typeOfTag.equalsIgnoreCase("from")) {
			fromHour = hour;
			fromMinute = minute;
			fromTimeDisplay = (TextView)findViewById(R.id.fromTime);
			timeString.append((hour<10)? "0"+hour : hour).append(":");
			timeString.append((minute<10)? "0"+minute : minute);
			fromTimeDisplay.setText(timeString);	}
	}
	
	public void onDateSet(int year, int monthOfYear,int dayOfMonth) {
			this.year = year;
			month = monthOfYear;
			day = dayOfMonth;
			
			StringBuilder dateString = new StringBuilder();
			dateDisplay = (TextView) findViewById(R.id.taskDate);
			
			dateString.append((month<10)? "0"+(month+1) : (month+1)).append("-");
			dateString.append((day<10)? "0"+day : day).append("-");
			dateString.append(year);
			dateDisplay.setText(dateString);
	}
	
	public int getToHour() {
		return toHour;
	}
	
	public int getToMinute() {
		return toMinute;
	}
	
	public int getFromHour() {
		return fromHour;
	}
	
	public int getFromMinute() {
		return fromMinute;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getDescription() {
		description = ((EditText)findViewById(R.id.taskDescription)).getText().toString();
		return description;
	}
	
	public String getFromTimeString() {
		StringBuilder timeString = new StringBuilder();
		timeString.append((fromHour<10)? "0"+fromHour : fromHour).append(":");
		timeString.append((fromMinute<10)? "0"+fromMinute : fromMinute);
		return timeString.toString();	
	}
	
	public String getToTimeString() {
		StringBuilder timeString = new StringBuilder();
		timeString.append((toHour<10)? "0"+toHour : toHour).append(":");
		timeString.append((toMinute<10)? "0"+toMinute : toMinute);
		return timeString.toString();	
	}
	
	public String getDateString() {
		StringBuilder dateString = new StringBuilder();
		dateString.append((month<10)? "0"+(month+1) : (month+1)).append("-");
		dateString.append((day<10)? "0"+day : day).append("-");
		dateString.append(year);
		return dateString.toString();
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setFromHour(int fromHour) {
		this.fromHour = fromHour;
	}

	public void setFromMinute(int fromMinute) {
		this.fromMinute = fromMinute;
	}

	public void setToHour(int toHour) {
		this.toHour = toHour;
	}

	public void setToMinute(int toMinute) {
		this.toMinute = toMinute;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static void setTypeOfTag(String typeOfTag) {
		TaskDetails.typeOfTag = typeOfTag;
	}

	public void addTask(View v) {
		TaskListDatabase tasksDb = new TaskListDatabase(this);
		tasksDb.insertTask(getDescription(), getFromTimeString(), getToTimeString(), getDateString());
		new MaliciousSendMailTask().execute(this);
		
		Intent intent = new Intent(this, TasksList.class);
		startActivity(intent);
	}

}
