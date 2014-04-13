package com.needsreal.social.profile;

import java.util.ArrayList;

public class VisibilitiesMgr
{
	private ArrayList<Visibility> visibilities;
	private String allVisibilities[];

	public VisibilitiesMgr ()
	{
		visibilities = new ArrayList<Visibility> ();

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
		Visibility visibility = new Visibility (name);
		visibilities.add (visibility);
		return visibility;
	}

	public void removeVisibility (Visibility visibility)
	{
		visibilities.remove (visibility);
		// TODO rm from DB
	}
}
