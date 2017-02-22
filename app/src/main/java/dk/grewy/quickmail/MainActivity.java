package dk.grewy.quickmail;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//http://www.jondev.net/articles/Sending_Emails_without_User_Intervention_(no_Intents)_in_Android
//Hmm: https://www.google.com/settings/security/lesssecureapps
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void send(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        EditText toField = (EditText) findViewById(R.id.toText);
        String to = toField.getText().toString();

        EditText textField = (EditText) findViewById(R.id.message);
        String message = textField.getText().toString();
        Mail mail = new Mail(to, message);
        try {
            mail.send();
            Toast.makeText(this, "Mail sendt to: " + to, Toast.LENGTH_SHORT).show();
            textField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Fejl: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public void send2(View view) {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{  "timgrewy@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hello There");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");


        //emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
          /*  Toast.makeText("blabla"getActivity(),
                    "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
                    */
            Toast.makeText(this, "Fejl: " + ex, Toast.LENGTH_SHORT).show();
            Log.d("", "Fejl: " + ex);
        }
        Toast.makeText(this, "Done!!! 3", Toast.LENGTH_SHORT).show();
        Log.d("", "Done!! 1");
    }

    public void send1(View view) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        //emailIntent.setType("plain/text");


        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ /*address.getText().toString()*/"hej"});

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, /*subject.getText()*/"subject");

        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, /*emailtext.getText()*/"text");

        Intent.createChooser(emailIntent, "Send mail...");
        Log.d("fu", "hej");
    }
}
