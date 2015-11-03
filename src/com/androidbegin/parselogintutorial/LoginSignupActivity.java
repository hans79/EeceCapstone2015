package com.androidbegin.parselogintutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignupActivity extends Activity {
	// Declare Variables
	Button loginbutton;
	String usernametxt;
	EditText username;
	ParseObject obj;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.loginsignup);
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.username);


		// Locate Buttons in main.xml
		loginbutton = (Button) findViewById(R.id.login);


		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Recipients");
				query.whereEqualTo("User", usernametxt);
				query.getFirstInBackground(new GetCallback<ParseObject>() {
				public void done(ParseObject object, ParseException e) {
				    if (object == null) {
				      Log.d( usernametxt,"no such user");
				    } else {
				    	setContentView(R.layout.welcome);
						TextView txtuser = (TextView) findViewById(R.id.txtuser);
						String boxid = object.getString("BoxID");
						// Set the currentUser String into TextView
						txtuser.setText("You are logged in as " + boxid);
						Button logout;
						logout = (Button) findViewById(R.id.logout);

						// Logout Button Click Listener
						logout.setOnClickListener(new OnClickListener() {

							public void onClick(View arg0) {
								// Logout current user
								Intent intent = new Intent(LoginSignupActivity.this,
										LoginSignupActivity.class);
										startActivity(intent);
										finish();
							}
						});
				    }
				  }
				});
			}
		});
	}
}
