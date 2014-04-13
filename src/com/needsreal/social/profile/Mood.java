package com.needsreal.social.profile;

import com.needsreal.social.R;

public enum Mood
{
	HAPPY (R.drawable.logo_menu),
	SAD   (R.drawable.logo_menu);
	// TODO

	private int resID;

	Mood (int resID)
	{
		this.resID = resID;
	}

	public int getDrawable ()
	{
		return resID;
	}
}
