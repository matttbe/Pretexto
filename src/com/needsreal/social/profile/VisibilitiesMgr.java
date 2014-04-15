package com.needsreal.social.profile;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;

import com.needsreal.social.Needsreal;
import com.needsreal.social.R;
import com.needsreal.social.profile.Visibility.VisibilityObject;

import android.content.Context;
import android.content.SharedPreferences.Editor;


public class VisibilitiesMgr
{
	private ArrayList<Visibility> visibilities;
	private Visibility currentGlobalVisibility;
	private Set<String> allVisibilities;

	private final Context context;

	// Keys for the settings
	private static final String VIS_DEFAULT = "default";
	private static final String VIS_FULL = "full";
	private static final String VIS_INVISIBLE = "invisible";

	public VisibilitiesMgr (Context context)
	{
		visibilities = new ArrayList<Visibility> ();
		this.context = context;
	}

	/**
	 * Init the visibilities manager by loading all visibilities
	 */
	public void init ()
	{
		allVisibilities = Needsreal.getSettings ().getStringSet (
				Needsreal.PREFS_KEY_VISIBILITIES, null);
		// first time we launch it
		if (allVisibilities == null || allVisibilities.size () == 0)
			loadFirstTime ();
		else
			loadFromDB ();
	}

	/**
	 * Remove all data from the settings
	 */
	public void clear ()
	{
		Editor editor = Needsreal.getSettings ().edit ();
		editor.remove (Needsreal.PREFS_KEY_CURRENT_GLOBAL_VIS);
		editor.remove (Needsreal.PREFS_KEY_VISIBILITIES);
		editor.apply ();

		for (Visibility visibility : visibilities)
			visibility.clearDB ();
	}

	/**
	 * Create and load default visibilities
	 */
	private void loadFirstTime ()
	{
		allVisibilities = new HashSet<String> (3);
		// Default
		Visibility visibility = newVisibility (VIS_DEFAULT,
				context.getString (R.string.vis_default));
		visibility.setPrecision (10);
		visibility.saveToDB ();

		setCurrentGlobalVisibility (visibility); // select as default one

		// Full
		visibility = newVisibility (VIS_FULL,
				context.getString (R.string.vis_full));

		// Invisible
		visibility = newVisibility (VIS_INVISIBLE,
				context.getString (R.string.vis_invisible));
		for (VisibilityObject item : visibility.getSettings ())
			item.visible = false;
		visibility.saveToDB ();

		sendAll ();
	}

	private void loadFromDB ()
	{
		String currentVisibility = Needsreal.getSettings ().getString (
				Needsreal.PREFS_KEY_CURRENT_GLOBAL_VIS, VIS_DEFAULT);
		boolean foundCurrent = false;
		for (String visibilityName : allVisibilities)
		{
			Visibility visibility = new Visibility (visibilityName, context);
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

		Editor editor = Needsreal.getSettings ().edit ();
		editor.putStringSet (Needsreal.PREFS_KEY_VISIBILITIES, allVisibilities);
		editor.apply ();
	}

	private Visibility newVisibility (String name, String title)
	{
		Visibility visibility = new Visibility (name, context);
		visibility.setTitle (title);
		addAndSaveVisibility (visibility);
		return visibility;
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
		return newVisibility (name.replaceAll ("[^A-Za-z0-9]", ""), name);
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
		visibility.clearDB ();

		boolean wasCurrent = visibility == currentGlobalVisibility;
		if (wasCurrent)
			setCurrentGlobalVisibility (visibilities.get (0));

		Editor editor = Needsreal.getSettings ().edit ();
		editor.putStringSet (Needsreal.PREFS_KEY_VISIBILITIES, allVisibilities);
		editor.apply ();

		return wasCurrent;
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

		Editor editor = Needsreal.getSettings ().edit ();
		editor.putString (Needsreal.PREFS_KEY_CURRENT_GLOBAL_VIS,
				visibility.getName ());
		editor.apply ();
	}
}
