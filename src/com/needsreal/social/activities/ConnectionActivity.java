package com.needsreal.social.activities;

import com.needsreal.social.Needsreal;
import com.needsreal.social.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ConnectionActivity extends Activity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);

		// Windows properties
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView (R.layout.activity_connection);

		// Text
		final EditText nicknameText = (EditText) findViewById (
				R.id.connection_username);
		final TextView passwordText = (EditText) findViewById (
				R.id.connection_password);

		// Button
		final Button connectionButton = (Button) findViewById (
				R.id.connection_connection_button);
		connectionButton.setOnClickListener (new View.OnClickListener ()
		{
			public void onClick (View v)
			{
				String nickname = nicknameText.getText ().toString ();
				String password = passwordText.getText ().toString ();
				if (nickname.isEmpty () || password.isEmpty ())
				{
					Toast.makeText (getApplicationContext (), "No name/pass",
							Toast.LENGTH_SHORT).show ();
					return;
				}

				Needsreal.getCurrentUser ().login (nickname, password);
				Toast.makeText (getApplicationContext (), "New user created",
						Toast.LENGTH_SHORT).show ();

				Intent intent = new Intent (ConnectionActivity.this,
						MapMainActivity.class);
				startActivity (intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater ().inflate (R.menu.connection, menu);
		return true;
	}

}
