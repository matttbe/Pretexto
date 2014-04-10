package com.needsreal.social.activities;

import com.needsreal.social.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class Message_activity extends Activity
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);

		// Windows properties
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.setContentView (R.layout.activity_message);
	}
}
