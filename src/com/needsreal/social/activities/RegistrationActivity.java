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
		setContentView (R.layout.activity_registration);

		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Buttons
		final Button registrationButton = (Button) findViewById (R.id.main_registration_button);
		registrationButton.setOnClickListener (new View.OnClickListener ()
		{
			public void onClick (View v)
			{
				Intent intent = new Intent (RegistrationActivity.this,
						RegistrationActivity.class); // TODO => activity
				startActivity (intent);
			}
		});
	}
}
