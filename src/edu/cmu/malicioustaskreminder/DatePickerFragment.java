package edu.cmu.malicioustaskreminder;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

	private OnDateSelectedListener parentActivity;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
       
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }
    
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof OnDateSelectedListener)
			parentActivity = (OnDateSelectedListener) activity;
	}
    
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		parentActivity.onDateSet(year, monthOfYear, dayOfMonth);
	}

}
