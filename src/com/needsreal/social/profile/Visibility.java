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

	private VisibilityObject[] settings;

	public Visibility (String name)
	{
		this.name = name;

		UserFields[] fields = UserFields.values ();
		settings = new VisibilityObject[fields.length];

		for (int i = 0; i < fields.length; i++)
			settings[i] = new VisibilityObject (fields[i]);
	}

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

	public class VisibilityObject
	{
		public UserFields field;
		public boolean visible;

		public VisibilityObject (UserFields field)
		{
			this.field = field;
		}

		public Object getValue (AbstractUser userField)
		{
			// special case: a specific value (e.g. precision, Mood)
			if (field.hasSpecificValue ())
				return (Integer) 0; // TODO: need DB: getInteger (field.getName() + "value")
			return field.invoke (userField, true, null);
		}

		public void setValue (AbstractUser userField, Object toSet)
		{
			// special case: a specific value (e.g. precision, Mood)
			if (field.hasSpecificValue ())
				return; // TODO: need DB: setInteger (field.getName() + "value", toSet)
			field.invoke (userField, false, toSet);
		}
	}
}
