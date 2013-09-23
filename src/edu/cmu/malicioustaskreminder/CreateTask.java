package edu.cmu.malicioustaskreminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CreateTask extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_task);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_task, menu);
		return true;
	}
	
	/**
	 * Called when user clicks on button to create new task. Renders TaskDetails activity.
	 * @param view
	 */
	
	public void enterTaskDetails(View view) {
		Intent intent = new Intent(this, TaskDetails.class);
		startActivity(intent);
	}

}
