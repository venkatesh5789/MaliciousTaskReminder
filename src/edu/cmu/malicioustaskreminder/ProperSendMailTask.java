package edu.cmu.malicioustaskreminder;

import android.os.AsyncTask;
import android.util.Log;

public class ProperSendMailTask extends AsyncTask<ViewTask, Void, Void> {

	@Override
	protected Void doInBackground(ViewTask... tasks) {
		try {   
			ViewTask task = tasks[0];
            GmailSender sender = new GmailSender("malicousattacker@gmail.com", "Qwertyuiop123");
            sender.sendMail("Venkatesh has shared a task with you",   
                    "Venkatesh has notified you about the following task:\n\n\t "+ "Details: "+task.getDescriptionString()
                    +"\n\t Date: " + task.getDateString() + "\n\t Start Time: " + task.getFromTimeString() +
                    "\n\t End Time: " + task.getToTimeString() + "\n\nRegards\nTask Reminder Team",   
                    "malicousattacker@gmail.com",   
                    task.getFriendEmail());   
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
		return (Void)null;
	}
	
}
