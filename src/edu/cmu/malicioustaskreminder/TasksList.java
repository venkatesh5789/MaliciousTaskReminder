package edu.cmu.malicioustaskreminder;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.malicioustaskreminder.db.TaskListDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TasksList extends Activity {
	private List<ViewTask> values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasks_list);

		final ListView listview = (ListView) findViewById(R.id.allTasksListView);
		
		TaskListDatabase tasksDb = new TaskListDatabase(getApplicationContext());
		tasksDb.open();
		values = tasksDb.getAllTasks();
		tasksDb.close();
		
		final ArrayList<String> titles = new ArrayList<String>();
		
		for(ViewTask view: values) {
			titles.add(view.getDescriptionString());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titles);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				String description = titles.get(position);
				Intent intent = new Intent(getApplicationContext(), ViewTask.class);
				intent.putExtra("taskDescription", description);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tasks_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_home:
			Intent intent = new Intent(getApplicationContext(), CreateTask.class);
			startActivity(intent);
			break;
		default:
			break;
		}

		return true;
	} 
}
