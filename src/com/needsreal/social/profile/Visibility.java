package com.needsreal.social.profile;

import com.needsreal.social.Needsreal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * The user can specify the visibility of some details of its profile and save
 * the selection with a name.
 *
 * What do we need?
 *  * Associate a setting (key-value) with a boolean.
 *  * List all settings with the value (e.g.: John) and the boolean (ON-OFF)
 *  * Save/Load settings
 *  * Send/Receive settings TODO: need Server
 *
 * Special cases:
 *  * Some settings can be specific, e.g. the precision, the mood (an Integer)
 *  * Some settings contain a list, e.g. the contact list.
 *    => We could enable only some of them TODO
 */
public class Visibility
{
	private String name; // can't contains specials chars

	private String title; // readable title
	private int precision = 0;
	private Mood mood = Mood.HAPPY; // save the string in the DB

	private VisibilityObject[] settings;
	private SharedPreferences prefs;

	private static final String PREFS_KEY_TITLE = "title";
	private static final String PREFS_KEY_PRECISION = "precisionValue";
	private static final String PREFS_KEY_MOOD = "moodValue";

	public Visibility (String name, Context context)
	{
		this.name = name;
		prefs = context.getSharedPreferences (Needsreal.PREFS_KEY_VISIBILITIES
				+ name, Context.MODE_PRIVATE);

		UserFields[] fields = UserFields.values ();
		settings = new VisibilityObject[fields.length];

		for (int i = 0; i < fields.length; i++)
			settings[i] = new VisibilityObject (fields[i]);
	}

	//____________________ GETTERS / SETTERS

	/**
	 * @return the name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * @return the settings
	 */
	public VisibilityObject[] getSettings ()
	{
		return settings;
	}

	//___________________________ SPECIFIC SETTINGS

	/**
	 * @return the title
	 */
	public String getTitle ()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle (String title)
	{
		this.title = title;
	}

	/**
	 * @return the precision value
	 */
	public int getPrecision ()
	{
		return precision;
	}

	/**
	 * @param precision the new precision value
	 */
	public void setPrecision (int precision)
	{
		this.precision = precision;
	}

	/**
	 * @return the drawable of the current Mood
	 */
	public int getMoodDrawable ()
	{
		return mood.getDrawable ();
	}

	/**
	 * @return the current Mood
	 */
	public Mood getMood ()
	{
		return mood;
	}

	/**
	 * @param mood Mood to set
	 */
	public void setMood (Mood mood)
	{
		this.mood = mood;
	}

	//___________________________ DB

	/**
	 * Save to the DataBase
	 */
	public void saveToDB ()
	{
		Editor editor = prefs.edit ();
		editor.putString (PREFS_KEY_TITLE, title);

		for (VisibilityObject item : settings)
			editor.putBoolean (item.field.getName (), item.visible);

		editor.putInt (PREFS_KEY_PRECISION, precision);
		editor.putString (PREFS_KEY_MOOD, mood.name ());
		editor.apply ();
	}

	/**
	 * Load values from the DataBase
	 */
	public void loadFromDB ()
	{
		for (VisibilityObject item : settings)
			item.visible = prefs.getBoolean (item.field.getName (), true);

		setTitle (prefs.getString (PREFS_KEY_TITLE, name));
		setPrecision (prefs.getInt (PREFS_KEY_PRECISION, 0));
		setMood (Mood.valueOf (prefs.getString (PREFS_KEY_MOOD,
				Mood.HAPPY.name ())));
	}

	//___________________________ Server

	/**
	 * send to the Server
	 */
	public void sendToServer ()
	{
		// TODO
	}

	/**
	 * Load values from the server
	 */
	public void loadFromServer ()
	{
		// TODO
	}

	//___________________________ CLASS

	public class VisibilityObject
	{
		public UserFields field;
		public boolean visible = true;

		public VisibilityObject (UserFields field)
		{
			this.field = field;
		}

		public Object getValue (AbstractUser userField)
		{
			// special case: a specific value (e.g. precision, Mood)
			if (field.hasSpecificValue ())
			{
				switch (field)
				{
					case PRECISION:
						return getPrecision ();
					case MOOD:
						return getMoodDrawable ();
					default:
						return null;
				}
			}
			return field.invoke (userField);
		}
	}
}
