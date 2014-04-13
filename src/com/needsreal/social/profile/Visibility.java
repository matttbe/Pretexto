package com.needsreal.social.profile;

/**
 * The user can specify the visibility of some details of its profile and save
 * the selection with a name.
 *
 * What do we need?
 *  * Associate a setting (key-value) with a boolean.
 *  * List all settings with the value (e.g.: John) and the boolean (ON-OFF)
 *  * Save/Load settings TODO: need DB
 *  * Send/Receive settings TODO: need Server
 *
 * Special cases:
 *  * Some settings can be specific, e.g. the precision, the mood (an Integer)
 *  * Some settings contain a list, e.g. the contact list.
 *    => We could enable only some of them TODO
 */
public class Visibility
{
	private String name;
	private int precision = 0;
	private Mood mood = Mood.HAPPY;

	private VisibilityObject[] settings;

	public Visibility (String name)
	{
		this.name = name;

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
	 * @param name the name to set
	 */
	public void setName (String name)
	{
		this.name = name;
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
		// TODO
		/*
		for (VisibilityObject item : settings)
			setBoolean (item.field.getName (), item.value);
		*/
	}

	/**
	 * Load values from the DataBase
	 */
	public void loadFromDB ()
	{
		// TODO
		/*
		for (VisibilityObject item : settings)
			item.value = getBoolean (item.field.getName ());
		*/
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
