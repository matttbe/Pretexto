package com.needsreal.social.profile;

import java.util.ArrayList;

import android.content.Context;

public class VisibilitiesMgr
{
	private ArrayList<Visibility> visibilities;
	private String allVisibilities[];

	public VisibilitiesMgr (Context context)
	{
		visibilities = new ArrayList<Visibility> ();

		// TODO: first time, init with: default, fullvisible, hidden + title from getString
		allVisibilities = "default,fullvisible,hidden".split (","); // TODO Get from DB
		for (String visibilityName : allVisibilities)
		{
			Visibility visibility = new Visibility (visibilityName);
			visibilities.add (visibility);
			visibility.loadFromDB (); // TODO: Get from Server too
		}
	}

	public Visibility addVisibility (String name) throws IllegalArgumentException
	{
		for (String visibility : allVisibilities)
		{
			if (name.equals (visibility))
				throw new IllegalArgumentException ();
		}
		// TODO check exceptions name (global name, etc.)
		// TODO save to DB
		Visibility visibility = new Visibility (name);
		visibilities.add (visibility);
		return visibility;
	}

	public void removeVisibility (Visibility visibility)
	{
		visibilities.remove (visibility);
		// TODO rm from DB: Pref + name in the list
	}

	public ArrayList<Visibility> getAll ()
	{
		return visibilities;
	}

	public void saveAll ()
	{
		for (Visibility visibility : visibilities)
			visibility.saveToDB ();
	}

	public void sendAll ()
	{
		for (Visibility visibility : visibilities)
			visibility.sendToServer ();
	}
}
