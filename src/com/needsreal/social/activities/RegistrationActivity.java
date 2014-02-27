package com.needsreal.social.activities;

import com.needsreal.social.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


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
				Intent intent = new Intent (RegistrationActivity.this,
						MapMainActivity.class); // TODO => activity
				startActivity (intent);
			}
		});
	}
	
	
	
}
