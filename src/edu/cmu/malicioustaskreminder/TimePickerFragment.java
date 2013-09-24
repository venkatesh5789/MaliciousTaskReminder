package edu.cmu.malicioustaskreminder;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	private OnTimeSelectedListener parentActivity;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		
		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
				DateFormat.is24HourFormat(getActivity()));
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof OnTimeSelectedListener)
			parentActivity = (OnTimeSelectedListener) activity;
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hour, int minute) {
		parentActivity.onTimeSet(hour, minute);		
	}

}
