package com.needsreal.social.activities;

import com.needsreal.social.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class RegistrationActivity extends Activity
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);

		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView (R.layout.activity_registration);

		// Buttons
		final Button registrationButton = (Button) findViewById (R.id.registration_button);
		registrationButton.setOnClickListener (new View.OnClickListener ()
		{
			public void onClick (View v)
			{
				Toast.makeText (getApplicationContext (), "TODO",
						Toast.LENGTH_SHORT).show ();
			}
		});
	}
	
	
	
}
