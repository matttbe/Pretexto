package com.needsreal.social.fragments;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.needsreal.social.R;


public class NewVisibilityObjectFragmentDialog extends DialogFragment
{
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate (R.layout.new_visibility_object_dialog,
				container, false);

		// Watch for button clicks.
		Button button = (Button) v.findViewById (R.id.validate);
		button.setOnClickListener (new OnClickListener ()
		{

			@Override
			public void onClick (View v)
			{

			}
		});

		return v;
	}

	public static NewVisibilityObjectFragmentDialog newInstance ()
	{

		NewVisibilityObjectFragmentDialog nv = new NewVisibilityObjectFragmentDialog ();

		return nv;

	}

}
