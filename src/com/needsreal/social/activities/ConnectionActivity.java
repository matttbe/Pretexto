package com.needsreal.social.activities;

import com.needsreal.social.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


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
		// Button
		final Button connectionButton = (Button) findViewById (R.id.connection_connection_button);
		connectionButton.setOnClickListener (new View.OnClickListener ()

		{
			public void onClick (View v)
			{
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
