package com.needsreal.social.profile;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;

import com.needsreal.social.Needsreal;
import com.needsreal.social.R;
import com.needsreal.social.profile.Visibility.VisibilityObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class VisibilitiesMgr
{
	private ArrayList<Visibility> visibilities;
	private Visibility currentGlobalVisibility;
	private Set<String> allVisibilities;

	private final SharedPreferences prefs;
	private final Context context;

	// Keys for the settings
	private static final String VIS_DEFAULT = "default";
	private static final String VIS_FULL = "full";
	private static final String VIS_INVISIBLE = "invisible";

	public VisibilitiesMgr (Context context, SharedPreferences settings)
	{
		visibilities = new ArrayList<Visibility> ();
		this.context = context;
		this.prefs = settings;

		allVisibilities = prefs.getStringSet (Needsreal.PREFS_KEY_VISIBILITIES,
				null);
		if (allVisibilities == null || allVisibilities.size () == 0) // first time we launch it
			loadFirstTime ();
		else
			loadFromDB ();
	}

	/**
	 * Create and load default visibilities
	 */
	private void loadFirstTime ()
	{
		allVisibilities = new HashSet<String> (3);
		// Default
		Visibility visibility = new Visibility (VIS_DEFAULT);
		visibility.setTitle (context.getString (R.string.vis_default));
		visibility.setPrecision (10);
		addAndSaveVisibility (visibility);

		setCurrentGlobalVisibility (visibility); // select as default one

		// Full
		visibility = new Visibility (VIS_FULL);
		visibility.setTitle (context.getString (R.string.vis_full));
		addAndSaveVisibility (visibility);

		// Invisible
		visibility = new Visibility (VIS_INVISIBLE);
		visibility.setTitle (context.getString (R.string.vis_invisible));
		for (VisibilityObject item : visibility.getSettings ())
			item.visible = false;
		addAndSaveVisibility (visibility);

		sendAll ();
	}

	private void loadFromDB ()
	{
		String currentVisibility = prefs.getString (
				Needsreal.PREFS_KEY_CURRENT_GLOBAL_VIS, VIS_DEFAULT);
		boolean foundCurrent = false;
		for (String visibilityName : allVisibilities)
		{
			Visibility visibility = new Visibility (visibilityName);
			visibilities.add (visibility);
			visibility.loadFromDB (); // TODO: Get from Server too

			if (! foundCurrent &&
					currentVisibility.equals (visibility.getName ()))
			{
				currentGlobalVisibility = visibility;
				foundCurrent = true;
			}
		}

		// should not happen
		if (currentGlobalVisibility == null)
			setCurrentGlobalVisibility (visibilities.get (0));
	}

	private void addAndSaveVisibility (Visibility visibility)
	{
		visibilities.add (visibility);
		allVisibilities.add (visibility.getName ());
		visibility.saveToDB ();

		Editor editor = prefs.edit ();
		editor.putStringSet (Needsreal.PREFS_KEY_VISIBILITIES, allVisibilities);
		editor.apply ();
	}

	/**
	 * Add a new visibility on the manager
	 * @param name the title of the visibility
	 * @return the new Visibility
	 * @throws IllegalArgumentException if the name already exists
	 */
	public Visibility addVisibility (String name)
			throws IllegalArgumentException
	{
		if (name == null || name.isEmpty ())
			throw new IllegalArgumentException ();

		for (String visibility : allVisibilities)
		{
			if (name.equals (visibility))
				throw new IllegalArgumentException ();
		}
		Visibility visibility = new Visibility (name.replaceAll (
				"[^A-Za-z0-9]", ""));
		visibility.setTitle (name);
		addAndSaveVisibility (visibility);
		return visibility;
	}

	/**
	 * Remove the visibility and delete associated resources
	 * @param visibility the visibility to delete
	 * @return true if the currentGlobalVisibility has been modified
	 * @throws EmptyStackException if we want to remove the last one.
	 */
	public boolean removeVisibility (Visibility visibility)
			throws EmptyStackException
	{
		if (visibilities.size () <= 1)
			throw new EmptyStackException ();

		visibilities.remove (visibility);
		allVisibilities.remove (visibility.getName ());
		boolean isCurrent = visibility == currentGlobalVisibility;
		if (isCurrent)
			setCurrentGlobalVisibility (visibilities.get (0));

		Editor editor = prefs.edit ();
		editor.putStringSet (Needsreal.PREFS_KEY_VISIBILITIES, allVisibilities);
		editor.apply ();

		return isCurrent;
	}

	/**
	 * @return all visibilities currently created
	 */
	public ArrayList<Visibility> getAll ()
	{
		return visibilities;
	}

	/**
	 * Save all visibilities to the DB
	 */
	public void saveAll ()
	{
		for (Visibility visibility : visibilities)
			visibility.saveToDB ();
	}

	/**
	 * Send all visibilities to the server
	 */
	public void sendAll ()
	{
		for (Visibility visibility : visibilities)
			visibility.sendToServer ();
	}

	/**
	 * @return the current and global visibility (for all default users)
	 */
	public Visibility getCurrentGlobalVisibility ()
	{
		return currentGlobalVisibility;
	}

	/**
	 * Set the current global visibility
	 */
	public void setCurrentGlobalVisibility (Visibility visibility)
	{
		currentGlobalVisibility = visibility;

		Editor editor = prefs.edit ();
		editor.putString (Needsreal.PREFS_KEY_CURRENT_GLOBAL_VIS,
				visibility.getName ());
		editor.apply ();
	}
}
