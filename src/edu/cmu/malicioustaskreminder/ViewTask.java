package edu.cmu.malicioustaskreminder;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cmu.malicioustaskreminder.db.TaskListDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTask extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	private TextView dateDisplay;
	private TextView fromTimeDisplay;
	private TextView toTimeDisplay;
	private TextView descriptionDisplay;
	private EditText friendEmail;
	
	private String descriptionString = "";
	private String fromTimeString = "";
	private String toTimeString = "";
	private String dateString = "";
	private String emailID = "";
	
	public ViewTask (TaskDetails t) {
		descriptionString = t.getDescription();
		fromTimeString = t.getFromTimeString();
		toTimeString = t.getToTimeString();
		dateString = t.getDateString();
		
		setVisibleDisplays();
	}
	
	public ViewTask (ViewTask v) {
		descriptionString = v.getDescriptionString();
		fromTimeString = v.getFromTimeString();
		toTimeString = v.getToTimeString();
		dateString = v.getDateString();
		
		setVisibleDisplays();
	}
	
	public ViewTask() {
	
	}
	
	private void setVisibleDisplays() {
		descriptionDisplay = (TextView)findViewById(R.id.taskDescription);
		fromTimeDisplay = (TextView)findViewById(R.id.fromTime);
		toTimeDisplay = (TextView)findViewById(R.id.toTime);
		dateDisplay = (TextView)findViewById(R.id.taskDate);
		
		dateDisplay.setText(dateString);
		descriptionDisplay.setText(descriptionString);
		toTimeDisplay.setText(toTimeString);
		fromTimeDisplay.setText(fromTimeString);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_task);
		
		Intent i = getIntent();
		if(i!=null) {
			String description = (String) i.getSerializableExtra("taskDescription") ;
			TaskListDatabase tasksDb = new TaskListDatabase(getApplicationContext());
			tasksDb.open();
			ViewTask v = tasksDb.getOneTask(description);
			tasksDb.close();
			
			descriptionString = v.getDescriptionString();
			fromTimeString = v.getFromTimeString();
			toTimeString = v.getToTimeString();
			dateString = v.getDateString();
			
			setVisibleDisplays();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_task, menu);
		return true;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public void setFromTimeString(String fromTimeString) {
		this.fromTimeString = fromTimeString;
	}

	public void setToTimeString(String toTimeString) {
		this.toTimeString = toTimeString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getDescriptionString(){
		return descriptionString;	
	}
	
	public String getFromTimeString() {
		return fromTimeString;
	}
	
	public String getToTimeString() {
		return toTimeString;	
	}
	
	public String getDateString() {
		return dateString;	
	}
	
	public String getFriendEmail() {
		return emailID;
	}
	
	public void emailFriend(View view) {
		friendEmail = (EditText)findViewById(R.id.friendEmail);
		emailID = friendEmail.getText().toString();
		if(isEmailValid(emailID)) {
			new ProperSendMailTask().execute(this);
			Toast.makeText(getApplicationContext(), "E-mail sent", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, CreateTask.class));
		} else {
			Toast.makeText(getApplicationContext(), "Invalid E-mail address syntax", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * method is used for checking valid email id format.
	 * 
	 * @param email
	 * @return boolean true for valid false for invalid
	 */
	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}

}
