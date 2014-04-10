package com.needsreal.social.profile;

import java.util.ArrayList;

public class UserSettings
{
	private ArrayList<Visibility> visibilities;

	public UserSettings ()
	{
		
	}

	public ArrayList<Visibility> getVisibilities ()
	{
		return this.visibilities;
	}

	public void removeVisibility (Visibility toRemove)
	{
		visibilities.remove (toRemove);
	}

	public void addVisibility (Visibility toAdd)
	{
		visibilities.add (toAdd);
	}
}
