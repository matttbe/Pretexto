package com.needsreal.social;

import android.content.Context;

import com.needsreal.social.search.StaticCategories;


public class Needsreal
{
	private static StaticCategories categories;
	private static boolean bInit = false;

	// can't be instantiate
	private Needsreal () {}

	/**
	 * Init some objects where a context is needed for the translations, etc.
	 * @param context of the application
	 */
	public static void init (Context context)
	{
		if (bInit)
			return;

		bInit = true;
		categories = new StaticCategories (context);
	}

	public static StaticCategories getCategories ()
	{
		return categories;
	}
}
