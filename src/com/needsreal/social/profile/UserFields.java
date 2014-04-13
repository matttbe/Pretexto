package com.needsreal.social.profile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

public enum UserFields
{
	HASH ("getHash"),
	NICKNAME ("getNickname"),
	FIRSTNAME ("getFirstName"),
	LASTNAME ("getLastName"),
	SHORTDESC ("getShortDescription"),
	DESC ("getDescription"),
	ACTIPRO ("getActivityPro"),
	CITY ("getCity"),
	POSTCODE ("getPostcode"),
	COUNTRY ("getCountry"),
	POSITION ("getPosition"),
	PRECISION (null),
	MOOD ("getMood"),
	INTERESTS ("getInterests"),
	NEEDS ("getNeeds"),
	CONTACTS ("getContacts");

	private final String methodName;

	UserFields (String method)
	{
		this.methodName = method;
	}

	public String getMethodName ()
	{
		return methodName;
	}

	public Object invoke (AbstractUser user)
	{
		Method method;
		try
		{
			method = user.getClass ().getMethod (methodName);
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
