package com.needsreal.social.activities;

import com.needsreal.social.Needsreal;
import com.needsreal.social.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends Activity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);

		Needsreal.init (getApplicationContext());

		// Windows properties
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView (R.layout.activity_main);

		// Buttons
		final Button registrationButton = (Button) findViewById (R.id.main_registration_button);
		registrationButton.setOnClickListener (new View.OnClickListener ()
		{
			public void onClick (View v)
			{
				Intent intent = new Intent (MainActivity.this,
						RegistrationActivity.class);
				startActivity (intent);
			}
		});

		final Button connectionButton = (Button) findViewById (R.id.main_connection_button);
		connectionButton.setOnClickListener (new View.OnClickListener ()
		{
			public void onClick (View v)
			{
				Intent intent = new Intent (MainActivity.this,
						ConnectionActivity.class); 
				startActivity (intent);
			}
		});

	}
}
