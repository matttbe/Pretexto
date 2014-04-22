package com.needsreal.social.profile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

public enum UserFields
{
	//         method       has list? specific value?
	NICKNAME  ("Nickname",    false, false),
	FIRSTNAME ("FirstName",   false, false),
	LASTNAME  ("LastName",    false, false),
	SHORTDESC ("ShortDesc",   false, false),
	DESC      ("Description", false, false),
	ACTIPRO   ("ActivityPro", false, false),
	POSTCODE  ("Postcode",    false, false),
	COUNTRY   ("Country",     false, false),
	POSITION  ("Position",    false, false),
	PRECISION ("Precision",   false, true),
	MOOD      ("Mood",        false, true),
	INTERESTS ("Interests",   true,  false),
	NEEDS     ("Needs",       true,  false),
	CONTACTS  ("Contacts",    true,  false);

	private final String name;
	private final boolean hasList;
	private final boolean specificValue;

	UserFields (String name, boolean hasList, boolean specificValue)
	{
		this.name = name;
		this.hasList = hasList;
		this.specificValue = specificValue;
	}

	/**
	 * @return true if this setting is linked to a list
	 */
	public boolean hasList ()
	{
		return hasList;
	}

	/**
	 * @return true if this setting has a specific value
	 * (for the visibility, not global)
	 */
	public boolean hasSpecificValue ()
	{
		return specificValue;
	}

	/**
	 * @return the method name associated to the setting
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * @param user current user with which we will interact
	 * @param get true if it's a getter method
	 * @param toSet the value to set in case of a setter method
	 * @return
	 */
	public Object invoke (AbstractUser user)
	{
		Method method;
		try
		{
			method = user.getClass ().getMethod ("get" + name);
		} catch (NoSuchMethodException e)
		{
			Log.e ("User", e.getMessage ());
			return null;
		}
		try
		{
			return method.invoke (user);
		} catch (IllegalAccessException e)
		{
			Log.e ("User", e.getMessage ());
		} catch (IllegalArgumentException e)
		{
			Log.e ("User", e.getMessage ());
		} catch (InvocationTargetException e)
		{
			Log.e ("User", e.getMessage ());
		}
		return null;
	}
}
